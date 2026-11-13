package test.java.infrastructure.persistence.memory;
import main.java.domain.enums.Prioridad;
import main.java.domain.enums.TipoServicio;
import main.java.domain.model.Cliente;
import main.java.domain.model.OrdenServicio;
import main.java.domain.model.Vehiculo;
import main.java.infrastructure.persistence.memory.InMemoryClienteRepository;
import main.java.infrastructure.persistence.memory.InMemoryOrdenRepository;
import main.java.infrastructure.persistence.memory.InMemoryVehiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoriosMemoriaTest {

    private InMemoryClienteRepository clienteRepository;
    private InMemoryVehiculoRepository vehiculoRepository;
    private InMemoryOrdenRepository ordenRepository;

    private Cliente cliente;
    private Vehiculo vehiculo;

    @BeforeEach
    void setUp() {
        clienteRepository = new InMemoryClienteRepository();
        vehiculoRepository = new InMemoryVehiculoRepository();
        ordenRepository = new InMemoryOrdenRepository();

        cliente = new Cliente("123", "Raul", "300123");
        vehiculo = new Vehiculo(
                "ABC123", "Toyota", "Corolla", cliente
        );
    }

    @Test
    void repositorioClienteDebeGuardarYConsultar() {
        clienteRepository.save(cliente);

        assertTrue(clienteRepository.existsByIdentificacion("123"));
        assertEquals(cliente,
                clienteRepository.findByIdentificacion("123").orElseThrow());
    }

    @Test
    void repositorioVehiculoDebeBuscarSinDistinguirMayusculas() {
        vehiculoRepository.save(vehiculo);

        assertTrue(vehiculoRepository.existsByPlaca("abc123"));
        assertEquals(vehiculo,
                vehiculoRepository.findByPlaca("abc123").orElseThrow());
    }

    @Test
    void repositorioVehiculoDebeEliminarCorrectamente() {
        vehiculoRepository.save(vehiculo);

        assertTrue(vehiculoRepository.deleteByPlaca("ABC123"));
        assertFalse(vehiculoRepository.existsByPlaca("ABC123"));
        assertFalse(vehiculoRepository.deleteByPlaca("ABC123"));
    }

    @Test
    void repositorioOrdenDebeGuardarYConsultar() {
        OrdenServicio orden = crearOrden(
                "ORD001", Prioridad.ALTA
        );

        ordenRepository.save(orden);

        assertTrue(ordenRepository.existsByCodigo("ord001"));
        assertEquals(orden,
                ordenRepository.findByCodigo("ORD001").orElseThrow());
    }

    @Test
    void debeRetornarPrimeroLaOrdenDeMayorPrioridad() {
        OrdenServicio baja = crearOrden(
                "ORD001", Prioridad.BAJA
        );

        OrdenServicio critica = crearOrden(
                "ORD002", Prioridad.CRITICA
        );

        ordenRepository.save(baja);
        ordenRepository.save(critica);

        assertEquals("ORD002",
                ordenRepository.findNextPending().orElseThrow().getCodigo());
    }

    @Test
    void noDebeIncluirOrdenesEnProcesoEnLaColaDePendientes() {
        OrdenServicio orden = crearOrden(
                "ORD001", Prioridad.ALTA
        );

        orden.iniciarServicio();
        ordenRepository.save(orden);

        assertTrue(ordenRepository.findNextPending().isEmpty());
    }

    @Test
    void debeDetectarOrdenesActivasPorPlaca() {
        OrdenServicio orden = crearOrden(
                "ORD001", Prioridad.ALTA
        );

        ordenRepository.save(orden);

        assertTrue(ordenRepository.existsActiveByVehiclePlate("abc123"));
    }

    @Test
    void noDebeConsiderarActivaUnaOrdenCompletada() {
        OrdenServicio orden = crearOrden(
                "ORD001", Prioridad.ALTA
        );

        orden.iniciarServicio();
        orden.completarServicio();
        ordenRepository.save(orden);

        assertFalse(ordenRepository.existsActiveByVehiclePlate("ABC123"));
    }

    private OrdenServicio crearOrden(String codigo, Prioridad prioridad) {
        return new OrdenServicio(
                codigo,
                "Falla en el motor",
                TipoServicio.REPARACION,
                prioridad,
                vehiculo
        );
    }
}