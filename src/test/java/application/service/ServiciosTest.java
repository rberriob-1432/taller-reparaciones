package test.java.application.service;
import main.java.application.service.ClienteService;
import main.java.application.service.OrdenService;
import main.java.application.service.VehiculoService;
import main.java.domain.enums.EstadoOrden;
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

class ServiciosTest {

    private InMemoryClienteRepository clienteRepository;
    private InMemoryVehiculoRepository vehiculoRepository;
    private InMemoryOrdenRepository ordenRepository;

    private ClienteService clienteService;
    private VehiculoService vehiculoService;
    private OrdenService ordenService;

    private Cliente cliente;
    private Vehiculo vehiculo;

    @BeforeEach
    void setUp() {
        clienteRepository = new InMemoryClienteRepository();
        vehiculoRepository = new InMemoryVehiculoRepository();
        ordenRepository = new InMemoryOrdenRepository();

        clienteService = new ClienteService(clienteRepository);

        vehiculoService = new VehiculoService(
                vehiculoRepository,
                clienteRepository,
                ordenRepository
        );

        ordenService = new OrdenService(
                ordenRepository,
                vehiculoRepository
        );

        cliente = new Cliente("123", "Raul Berrio", "3001234567");
        clienteRepository.save(cliente);

        vehiculo = new Vehiculo(
                "ABC123", "Toyota", "Corolla", cliente
        );
        vehiculoRepository.save(vehiculo);
    }

    // PRUEBAS DE CLIENTE

    @Test
    void debeRegistrarUnCliente() {
        Cliente nuevo = new Cliente("456", "Carlos", "300555");

        clienteService.registrarCliente(nuevo);

        assertEquals(nuevo,
                clienteService.consultarCliente("456"));
    }

    @Test
    void noDebeRegistrarClientesConIdentificacionDuplicada() {
        Cliente duplicado = new Cliente(
                "123", "Otro nombre", "300999"
        );

        assertThrows(IllegalArgumentException.class,
                () -> clienteService.registrarCliente(duplicado));
    }

    @Test
    void debeConsultarUnClienteRegistrado() {
        assertEquals(cliente,
                clienteService.consultarCliente("123"));
    }

    @Test
    void debeLanzarExcepcionSiElClienteNoExiste() {
        assertThrows(IllegalArgumentException.class,
                () -> clienteService.consultarCliente("999"));
    }

    @Test
    void debeListarLosClientes() {
        assertEquals(1, clienteService.listarClientes().size());
    }

    // PRUEBAS DE VEHICULO

    @Test
    void debeRegistrarUnVehiculoConPropietarioExistente() {
        Vehiculo nuevo = new Vehiculo(
                "XYZ789", "Mazda", "3", cliente
        );

        vehiculoService.registrarVehiculo(nuevo);

        assertTrue(vehiculoRepository.existsByPlaca("XYZ789"));
    }

    @Test
    void noDebeRegistrarUnVehiculoConPlacaDuplicada() {
        Vehiculo duplicado = new Vehiculo(
                "ABC123", "Mazda", "3", cliente
        );

        assertThrows(IllegalArgumentException.class,
                () -> vehiculoService.registrarVehiculo(duplicado));
    }

    @Test
    void noDebeRegistrarUnVehiculoConPropietarioNoRegistrado() {
        Cliente desconocido = new Cliente(
                "999", "Desconocido", "300999"
        );

        Vehiculo nuevo = new Vehiculo(
                "XYZ789", "Mazda", "3", desconocido
        );

        assertThrows(IllegalArgumentException.class,
                () -> vehiculoService.registrarVehiculo(nuevo));
    }

    @Test
    void debeConsultarUnVehiculoRegistrado() {
        assertEquals(vehiculo,
                vehiculoService.consultarVehiculo("ABC123"));
    }

    @Test
    void debeEliminarUnVehiculoSinOrdenesActivas() {
        vehiculoService.eliminarVehiculo("ABC123");

        assertFalse(vehiculoRepository.existsByPlaca("ABC123"));
    }

    @Test
    void noDebeEliminarUnVehiculoConOrdenPendiente() {
        ordenService.registrarOrden(
                "ORD001",
                "Falla de motor",
                TipoServicio.REPARACION,
                Prioridad.ALTA,
                "ABC123"
        );

        assertThrows(IllegalStateException.class,
                () -> vehiculoService.eliminarVehiculo("ABC123"));
    }

    // PRUEBAS DE ORDEN DE SERVICIO

    @Test
    void debeRegistrarUnaOrden() {
        ordenService.registrarOrden(
                "ORD001",
                "Falla de motor",
                TipoServicio.REPARACION,
                Prioridad.ALTA,
                "ABC123"
        );

        assertEquals(1, ordenService.listarOrdenes().size());
    }

    @Test
    void noDebeRegistrarUnaOrdenConCodigoDuplicado() {
        ordenService.registrarOrden(
                "ORD001", "Falla", TipoServicio.REPARACION,
                Prioridad.ALTA, "ABC123"
        );

        assertThrows(IllegalArgumentException.class,
                () -> ordenService.registrarOrden(
                        "ORD001", "Otra falla", TipoServicio.DIAGNOSTICO,
                        Prioridad.MEDIA, "ABC123"
                ));
    }

    @Test
    void noDebeRegistrarUnaOrdenParaUnVehiculoInexistente() {
        assertThrows(IllegalArgumentException.class,
                () -> ordenService.registrarOrden(
                        "ORD002", "Falla", TipoServicio.REPARACION,
                        Prioridad.ALTA, "NOEXISTE"
                ));
    }

    @Test
    void debeProcesarLaSiguienteOrdenPendiente() {
        ordenService.registrarOrden(
                "ORD001", "Falla", TipoServicio.REPARACION,
                Prioridad.ALTA, "ABC123"
        );

        OrdenServicio procesada =
                ordenService.procesarSiguienteOrden();

        assertEquals(EstadoOrden.EN_PROCESO, procesada.getEstado());
    }

    @Test
    void debeLanzarExcepcionCuandoNoHayOrdenesPendientes() {
        assertThrows(IllegalStateException.class,
                () -> ordenService.procesarSiguienteOrden());
    }

    @Test
    void debeCambiarLaPrioridadDeUnaOrdenPendiente() {
        ordenService.registrarOrden(
                "ORD001", "Falla", TipoServicio.REPARACION,
                Prioridad.BAJA, "ABC123"
        );

        ordenService.cambiarPrioridad("ORD001", Prioridad.CRITICA);

        assertEquals(Prioridad.CRITICA,
                ordenService.consultarOrden("ORD001").getPrioridad());
    }

    @Test
    void noDebeCambiarLaPrioridadDeUnaOrdenEnProceso() {
        ordenService.registrarOrden(
                "ORD001", "Falla", TipoServicio.REPARACION,
                Prioridad.ALTA, "ABC123"
        );

        ordenService.procesarSiguienteOrden();

        assertThrows(IllegalStateException.class,
                () -> ordenService.cambiarPrioridad(
                        "ORD001", Prioridad.CRITICA
                ));
    }

    @Test
    void debeCompletarUnaOrdenEnProceso() {
        ordenService.registrarOrden(
                "ORD001", "Falla", TipoServicio.REPARACION,
                Prioridad.ALTA, "ABC123"
        );

        ordenService.procesarSiguienteOrden();
        ordenService.completarOrden("ORD001");

        assertEquals(EstadoOrden.COMPLETADA,
                ordenService.consultarOrden("ORD001").getEstado());
    }

    @Test
    void debeCancelarUnaOrdenPendiente() {
        ordenService.registrarOrden(
                "ORD001", "Falla", TipoServicio.REPARACION,
                Prioridad.ALTA, "ABC123"
        );

        ordenService.cancelarOrden("ORD001");

        assertEquals(EstadoOrden.CANCELADA,
                ordenService.consultarOrden("ORD001").getEstado());
    }
}