package test.java.presentation;
import main.java.application.service.OrdenService;
import main.java.domain.enums.EstadoOrden;
import main.java.domain.enums.Prioridad;
import main.java.domain.enums.TipoServicio;
import main.java.domain.model.Cliente;
import main.java.domain.model.Vehiculo;
import main.java.infrastructure.persistence.memory.InMemoryOrdenRepository;
import main.java.infrastructure.persistence.memory.InMemoryVehiculoRepository;
import main.java.presentation.OrdenController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class OrdenControllerTest {

    private OrdenController controller;
    private OrdenService service;

    private final ByteArrayOutputStream salida =
            new ByteArrayOutputStream();

    private final PrintStream salidaOriginal = System.out;

    @BeforeEach
    void setUp() {
        InMemoryOrdenRepository ordenRepository =
                new InMemoryOrdenRepository();

        InMemoryVehiculoRepository vehiculoRepository =
                new InMemoryVehiculoRepository();

        Cliente cliente = new Cliente(
                "123", "Raul", "3001234567"
        );

        Vehiculo vehiculo = new Vehiculo(
                "ABC123", "Toyota", "Corolla", cliente
        );

        vehiculoRepository.save(vehiculo);

        service = new OrdenService(
                ordenRepository,
                vehiculoRepository
        );

        controller = new OrdenController(service);

        System.setOut(new PrintStream(salida));
    }

    @Test
    void debeRegistrarUnaOrden() {
        controller.registrar(
                "ORD001",
                "Falla del motor",
                TipoServicio.REPARACION,
                Prioridad.ALTA,
                "ABC123"
        );

        assertEquals(1, service.listarOrdenes().size());

        assertTrue(salida.toString().contains(
                "Orden registrada correctamente."
        ));
    }

    @Test
    void debeConsultarUnaOrden() {
        registrarOrden();

        salida.reset();
        controller.consultar("ORD001");

        assertTrue(salida.toString().contains("ORD001"));
    }

    @Test
    void debeListarLasOrdenes() {
        registrarOrden();

        salida.reset();
        controller.listar();

        assertTrue(salida.toString().contains("ORD001"));
    }

    @Test
    void debeProcesarLaSiguienteOrden() {
        registrarOrden();

        salida.reset();
        controller.procesarSiguiente();

        assertEquals(
                EstadoOrden.EN_PROCESO,
                service.consultarOrden("ORD001").getEstado()
        );

        assertTrue(salida.toString().contains(
                "Orden enviada a reparación:"
        ));
    }

    @Test
    void debeCambiarLaPrioridadDeUnaOrden() {
        registrarOrden();

        controller.cambiarPrioridad("ORD001", Prioridad.CRITICA);

        assertEquals(
                Prioridad.CRITICA,
                service.consultarOrden("ORD001").getPrioridad()
        );

        assertTrue(salida.toString().contains(
                "Prioridad actualizada correctamente."
        ));
    }

    @Test
    void debeCompletarUnaOrden() {
        registrarOrden();
        service.procesarSiguienteOrden();

        salida.reset();
        controller.completar("ORD001");

        assertEquals(
                EstadoOrden.COMPLETADA,
                service.consultarOrden("ORD001").getEstado()
        );

        assertTrue(salida.toString().contains(
                "Servicio completado."
        ));
    }

    @Test
    void debeCancelarUnaOrden() {
        registrarOrden();

        controller.cancelar("ORD001");

        assertEquals(
                EstadoOrden.CANCELADA,
                service.consultarOrden("ORD001").getEstado()
        );

        assertTrue(salida.toString().contains(
                "Orden cancelada."
        ));
    }

    @Test
    void debePropagarErroresAlConsultarUnaOrdenInexistente() {
        assertThrows(IllegalArgumentException.class,
                () -> controller.consultar("ORD999"));
    }

    private void registrarOrden() {
        service.registrarOrden(
                "ORD001",
                "Falla del motor",
                TipoServicio.REPARACION,
                Prioridad.ALTA,
                "ABC123"
        );
    }

    @AfterEach
    void restaurarSalida() {
        System.setOut(salidaOriginal);
    }
}