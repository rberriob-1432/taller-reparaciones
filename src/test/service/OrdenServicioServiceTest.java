package test.service;
import main.estructuras.ArrayList;
import main.model.*;
import main.repository.*;
import main.service.OrdenServicioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrdenServicioServiceTest {

    private OrdenServicioRepository ordenRepository;
    private VehiculoRepository vehiculoRepository;
    private ClienteRepository clienteRepository;
    private OrdenServicioService service;

    private Vehiculo vehiculo;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        ordenRepository = new OrdenServicioRepository();
        vehiculoRepository = new VehiculoRepository();
        clienteRepository = new ClienteRepository();

        vehiculo = new Vehiculo(
                "ABC123", "Toyota", "Corolla", 2022, "Gris"
        );

        cliente = new Cliente(
                "1001", "Carlos Perez", "3001234567", "carlos@email.com"
        );

        vehiculoRepository.registrar(vehiculo);
        clienteRepository.registrar(cliente);

        service = new OrdenServicioService(
                ordenRepository,
                vehiculoRepository,
                clienteRepository
        );
    }

    private OrdenServicio crearOrden(
            String codigo, Prioridad prioridad) {
        return new OrdenServicio(
                codigo,
                "Falla en el motor",
                TipoServicio.REPARACION_MOTOR,
                prioridad,
                vehiculo,
                cliente
        );
    }

    @Test
    void debeCrearServicioConRepositoriosValidos() {
        assertNotNull(service);
    }

    @Test
    void noDebeCrearServicioConRepositoriosNulos() {
        assertThrows(IllegalArgumentException.class,
                () -> new OrdenServicioService(
                        null, vehiculoRepository, clienteRepository));

        assertThrows(IllegalArgumentException.class,
                () -> new OrdenServicioService(
                        ordenRepository, null, clienteRepository));

        assertThrows(IllegalArgumentException.class,
                () -> new OrdenServicioService(
                        ordenRepository, vehiculoRepository, null));
    }

    @Test
    void debeRegistrarOrdenConReferenciasValidas() {
        service.registrar(crearOrden("ORD001", Prioridad.ALTA));

        assertEquals(1, service.cantidad());
    }

    @Test
    void noDebeRegistrarOrdenConVehiculoNoRegistrado() {
        Vehiculo noRegistrado = new Vehiculo(
                "XYZ999", "Mazda", "CX5", 2023, "Negro"
        );

        OrdenServicio orden = new OrdenServicio(
                "ORD001",
                "Falla de motor",
                TipoServicio.REPARACION_MOTOR,
                Prioridad.ALTA,
                noRegistrado,
                cliente
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.registrar(orden));
    }

    @Test
    void noDebeRegistrarOrdenConClienteNoRegistrado() {
        Cliente noRegistrado = new Cliente(
                "9999", "Maria Lopez", "3111111111", "maria@email.com"
        );

        OrdenServicio orden = new OrdenServicio(
                "ORD001",
                "Falla de motor",
                TipoServicio.REPARACION_MOTOR,
                Prioridad.ALTA,
                vehiculo,
                noRegistrado
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.registrar(orden));
    }

    @Test
    void noDebeRegistrarOrdenNula() {
        assertThrows(IllegalArgumentException.class,
                () -> service.registrar(null));
    }

    @Test
    void debeBuscarOrdenPorCodigo() {
        OrdenServicio orden = crearOrden("ORD001", Prioridad.ALTA);
        service.registrar(orden);

        assertEquals(orden, service.buscarPorCodigo("ORD001"));
    }

    @Test
    void debeRetornarNullCuandoOrdenNoExiste() {
        assertNull(service.buscarPorCodigo("ORD999"));
    }

    @Test
    void debeActualizarOrden() {
        service.registrar(crearOrden("ORD001", Prioridad.ALTA));

        OrdenServicio actualizada =
                crearOrden("ORD001", Prioridad.CRITICA);

        assertTrue(service.actualizar(actualizada));
        assertEquals(Prioridad.CRITICA,
                service.buscarPorCodigo("ORD001").getPrioridad());
    }

    @Test
    void noDebeActualizarOrdenConReferenciasInvalidas() {
        Vehiculo noRegistrado = new Vehiculo(
                "XYZ999", "Mazda", "CX5", 2023, "Negro"
        );

        OrdenServicio orden = new OrdenServicio(
                "ORD001",
                "Falla de motor",
                TipoServicio.REPARACION_MOTOR,
                Prioridad.ALTA,
                noRegistrado,
                cliente
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.actualizar(orden));
    }

    @Test
    void debeEliminarOrden() {
        service.registrar(crearOrden("ORD001", Prioridad.ALTA));

        assertTrue(service.eliminar("ORD001"));
        assertEquals(0, service.cantidad());
    }

    @Test
    void noDebeEliminarOrdenInexistente() {
        assertFalse(service.eliminar("ORD999"));
    }

    @Test
    void debeCambiarEstadoDeOrden() {
        service.registrar(crearOrden("ORD001", Prioridad.ALTA));

        assertTrue(service.cambiarEstado(
                "ORD001", EstadoOrden.EN_REPARACION));

        assertEquals(EstadoOrden.EN_REPARACION,
                service.buscarPorCodigo("ORD001").getEstado());
    }

    @Test
    void noDebeCambiarEstadoDeOrdenInexistente() {
        assertFalse(service.cambiarEstado(
                "ORD999", EstadoOrden.EN_REPARACION));
    }

    @Test
    void debeCambiarPrioridadDeOrden() {
        service.registrar(crearOrden("ORD001", Prioridad.BAJA));

        assertTrue(service.cambiarPrioridad(
                "ORD001", Prioridad.CRITICA));

        assertEquals(Prioridad.CRITICA,
                service.buscarPorCodigo("ORD001").getPrioridad());
    }

    @Test
    void noDebeCambiarPrioridadDeOrdenInexistente() {
        assertFalse(service.cambiarPrioridad(
                "ORD999", Prioridad.CRITICA));
    }

    @Test
    void debeListarOrdenesPorPrioridad() {
        service.registrar(crearOrden("ORD001", Prioridad.BAJA));
        service.registrar(crearOrden("ORD002", Prioridad.CRITICA));
        service.registrar(crearOrden("ORD003", Prioridad.MEDIA));

        ArrayList<OrdenServicio> resultado =
                service.listarPorPrioridad();

        assertEquals(3, resultado.size());
        assertEquals("ORD002", resultado.get(0).getCodigo());
        assertEquals("ORD003", resultado.get(1).getCodigo());
        assertEquals("ORD001", resultado.get(2).getCodigo());
    }

    @Test
    void debeRetornarCantidadDeOrdenes() {
        service.registrar(crearOrden("ORD001", Prioridad.ALTA));
        service.registrar(crearOrden("ORD002", Prioridad.BAJA));

        assertEquals(2, service.cantidad());
    }
}
