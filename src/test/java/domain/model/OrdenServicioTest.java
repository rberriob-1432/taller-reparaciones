package test.java.domain.model;
import main.java.domain.enums.EstadoOrden;
import main.java.domain.enums.Prioridad;
import main.java.domain.enums.TipoServicio;
import main.java.domain.model.Cliente;
import main.java.domain.model.OrdenServicio;
import main.java.domain.model.Vehiculo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrdenServicioTest {

    private Vehiculo crearVehiculo() {
        Cliente cliente = new Cliente("123", "Raul", "3001234567");
        return new Vehiculo("ABC123", "Toyota", "Corolla", cliente);
    }

    private OrdenServicio crearOrden() {
        return new OrdenServicio(
                "ORD001",
                "Falla en el motor",
                TipoServicio.REPARACION,
                Prioridad.ALTA,
                crearVehiculo()
        );
    }

    @Test
    void debeCrearOrdenEnEstadoPendiente() {
        OrdenServicio orden = crearOrden();

        assertEquals("ORD001", orden.getCodigo());
        assertEquals(EstadoOrden.PENDIENTE, orden.getEstado());
        assertEquals(Prioridad.ALTA, orden.getPrioridad());
    }

    @Test
    void debeNormalizarElCodigo() {
        OrdenServicio orden = new OrdenServicio(
                " ord001 ",
                "Falla en el motor",
                TipoServicio.REPARACION,
                Prioridad.MEDIA,
                crearVehiculo()
        );

        assertEquals("ORD001", orden.getCodigo());
    }

    @Test
    void debeRechazarCodigoVacio() {
        assertThrows(IllegalArgumentException.class,
                () -> new OrdenServicio(
                        " ", "Falla", TipoServicio.REPARACION,
                        Prioridad.ALTA, crearVehiculo()
                ));
    }

    @Test
    void debeRechazarTipoServicioNulo() {
        assertThrows(NullPointerException.class,
                () -> new OrdenServicio(
                        "ORD001", "Falla", null,
                        Prioridad.ALTA, crearVehiculo()
                ));
    }

    @Test
    void debeCambiarLaPrioridad() {
        OrdenServicio orden = crearOrden();

        orden.cambiarPrioridad(Prioridad.CRITICA);

        assertEquals(Prioridad.CRITICA, orden.getPrioridad());
    }

    @Test
    void debeIniciarUnaOrdenPendiente() {
        OrdenServicio orden = crearOrden();

        orden.iniciarServicio();

        assertEquals(EstadoOrden.EN_PROCESO, orden.getEstado());
    }

    @Test
    void noDebeIniciarUnaOrdenQueYaEstaEnProceso() {
        OrdenServicio orden = crearOrden();
        orden.iniciarServicio();

        assertThrows(IllegalStateException.class,
                orden::iniciarServicio);
    }

    @Test
    void debeCompletarUnaOrdenEnProceso() {
        OrdenServicio orden = crearOrden();
        orden.iniciarServicio();

        orden.completarServicio();

        assertEquals(EstadoOrden.COMPLETADA, orden.getEstado());
    }

    @Test
    void noDebeCompletarUnaOrdenPendiente() {
        OrdenServicio orden = crearOrden();

        assertThrows(IllegalStateException.class,
                orden::completarServicio);
    }

    @Test
    void debeCancelarUnaOrdenPendiente() {
        OrdenServicio orden = crearOrden();

        orden.cancelarServicio();

        assertEquals(EstadoOrden.CANCELADA, orden.getEstado());
    }

    @Test
    void noDebeCancelarUnaOrdenCompletada() {
        OrdenServicio orden = crearOrden();
        orden.iniciarServicio();
        orden.completarServicio();

        assertThrows(IllegalStateException.class,
                orden::cancelarServicio);
    }

    @Test
    void debeOrdenarPorPrioridadDeMayorAMenor() {
        OrdenServicio baja = new OrdenServicio(
                "ORD001", "Falla menor", TipoServicio.DIAGNOSTICO,
                Prioridad.BAJA, crearVehiculo()
        );

        OrdenServicio critica = new OrdenServicio(
                "ORD002", "Falla urgente", TipoServicio.REPARACION,
                Prioridad.CRITICA, crearVehiculo()
        );

        assertTrue(critica.compareTo(baja) < 0);
    }
}