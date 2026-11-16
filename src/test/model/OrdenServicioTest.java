package test.model;

import main.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class OrdenServicioTest {

    private Vehiculo vehiculo;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        vehiculo = new Vehiculo(
                "ABC123", "Toyota", "Corolla", 2022, "Gris"
        );

        cliente = new Cliente(
                "1001", "Carlos Perez", "3001234567", "carlos@email.com"
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
    void debeCrearOrdenCorrectamente() {
        OrdenServicio orden = crearOrden("ORD001", Prioridad.ALTA);

        assertEquals("ORD001", orden.getCodigo());
        assertEquals("Falla en el motor", orden.getDescripcionFalla());
        assertEquals(TipoServicio.REPARACION_MOTOR, orden.getTipoServicio());
        assertEquals(Prioridad.ALTA, orden.getPrioridad());
        assertEquals(EstadoOrden.PENDIENTE, orden.getEstado());
        assertEquals(vehiculo, orden.getVehiculo());
        assertEquals(cliente, orden.getCliente());
    }

    @Test
    void noDebeCrearOrdenConCodigoNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                new OrdenServicio(
                        null, "Falla", TipoServicio.DIAGNOSTICO,
                        Prioridad.ALTA, vehiculo, cliente
                )
        );
    }

    @Test
    void noDebeCrearOrdenConCodigoVacio() {
        assertThrows(IllegalArgumentException.class, () ->
                new OrdenServicio(
                        " ", "Falla", TipoServicio.DIAGNOSTICO,
                        Prioridad.ALTA, vehiculo, cliente
                )
        );
    }

    @Test
    void noDebeCrearOrdenConDescripcionNula() {
        assertThrows(IllegalArgumentException.class, () ->
                new OrdenServicio(
                        "ORD001", null, TipoServicio.DIAGNOSTICO,
                        Prioridad.ALTA, vehiculo, cliente
                )
        );
    }

    @Test
    void debeIniciarConEstadoPendiente() {
        OrdenServicio orden = crearOrden("ORD001", Prioridad.MEDIA);

        assertEquals(EstadoOrden.PENDIENTE, orden.getEstado());
    }

    @Test
    void debePermitirActualizarPrioridad() {
        OrdenServicio orden = crearOrden("ORD001", Prioridad.BAJA);

        orden.setPrioridad(Prioridad.CRITICA);

        assertEquals(Prioridad.CRITICA, orden.getPrioridad());
    }

    @Test
    void noDebeAceptarPrioridadNula() {
        OrdenServicio orden = crearOrden("ORD001", Prioridad.MEDIA);

        assertThrows(IllegalArgumentException.class, () ->
                orden.setPrioridad(null)
        );
    }

    @Test
    void debePermitirActualizarEstado() {
        OrdenServicio orden = crearOrden("ORD001", Prioridad.ALTA);

        orden.setEstado(EstadoOrden.EN_REPARACION);

        assertEquals(EstadoOrden.EN_REPARACION, orden.getEstado());
    }

    @Test
    void noDebeAceptarEstadoNulo() {
        OrdenServicio orden = crearOrden("ORD001", Prioridad.ALTA);

        assertThrows(IllegalArgumentException.class, () ->
                orden.setEstado(null)
        );
    }

    @Test
    void debeOrdenarPrimeroLaPrioridadCritica() {
        OrdenServicio critica = crearOrden("ORD003", Prioridad.CRITICA);
        OrdenServicio baja = crearOrden("ORD001", Prioridad.BAJA);

        assertTrue(critica.compareTo(baja) < 0);
    }

    @Test
    void debeOrdenarPorCodigoCuandoLaPrioridadEsIgual() {
        OrdenServicio primera = crearOrden("ORD001", Prioridad.ALTA);
        OrdenServicio segunda = crearOrden("ORD002", Prioridad.ALTA);

        assertTrue(primera.compareTo(segunda) < 0);
    }

    @Test
    void debeRetornarCeroAlCompararLaMismaOrden() {
        OrdenServicio orden = crearOrden("ORD001", Prioridad.MEDIA);

        assertEquals(0, orden.compareTo(orden));
    }
}
