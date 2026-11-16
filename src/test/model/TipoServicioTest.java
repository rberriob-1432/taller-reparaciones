package test.model;
import main.model.TipoServicio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoServicioTest {

    @Test
    void debeContenerOchoTiposDeServicio() {
        assertEquals(8, TipoServicio.values().length);
    }

    @Test
    void debeContenerMantenimientoPreventivo() {
        assertNotNull(TipoServicio.valueOf("MANTENIMIENTO_PREVENTIVO"));
    }

    @Test
    void debeContenerMantenimientoCorrectivo() {
        assertNotNull(TipoServicio.valueOf("MANTENIMIENTO_CORRECTIVO"));
    }

    @Test
    void debeContenerDiagnostico() {
        assertNotNull(TipoServicio.valueOf("DIAGNOSTICO"));
    }

    @Test
    void debeContenerReparacionMotor() {
        assertNotNull(TipoServicio.valueOf("REPARACION_MOTOR"));
    }

    @Test
    void debeContenerReparacionFrenos() {
        assertNotNull(TipoServicio.valueOf("REPARACION_FRENOS"));
    }

    @Test
    void debeContenerElectricidad() {
        assertNotNull(TipoServicio.valueOf("ELECTRICIDAD"));
    }

    @Test
    void debeContenerCambioAceite() {
        assertNotNull(TipoServicio.valueOf("CAMBIO_ACEITE"));
    }

    @Test
    void debeContenerLatoneriaYPintura() {
        assertNotNull(TipoServicio.valueOf("LATONERIA_Y_PINTURA"));
    }
}
