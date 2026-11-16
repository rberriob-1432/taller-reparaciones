package test.model;
import main.model.EstadoOrden;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadoOrdenTest {

    @Test
    void debeContenerCincoEstados() {
        assertEquals(5, EstadoOrden.values().length);
    }

    @Test
    void debeContenerEstadoPendiente() {
        assertNotNull(EstadoOrden.valueOf("PENDIENTE"));
    }

    @Test
    void debeContenerEstadoEnDiagnostico() {
        assertNotNull(EstadoOrden.valueOf("EN_DIAGNOSTICO"));
    }

    @Test
    void debeContenerEstadoEnReparacion() {
        assertNotNull(EstadoOrden.valueOf("EN_REPARACION"));
    }

    @Test
    void debeContenerEstadoFinalizada() {
        assertNotNull(EstadoOrden.valueOf("FINALIZADA"));
    }

    @Test
    void debeContenerEstadoCancelada() {
        assertNotNull(EstadoOrden.valueOf("CANCELADA"));
    }
}
