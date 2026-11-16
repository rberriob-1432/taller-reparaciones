package test.model;
import main.model.Prioridad;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrioridadTest {

    @Test
    void debeContenerCuatroPrioridades() {
        assertEquals(4, Prioridad.values().length);
    }

    @Test
    void debeAsignarNivelCorrecto() {
        assertEquals(1, Prioridad.CRITICA.getNivel());
        assertEquals(2, Prioridad.ALTA.getNivel());
        assertEquals(3, Prioridad.MEDIA.getNivel());
        assertEquals(4, Prioridad.BAJA.getNivel());
    }

    @Test
    void prioridadCriticaDebeSerLaMasAlta() {
        assertTrue(
                Prioridad.CRITICA.getNivel() < Prioridad.ALTA.getNivel()
        );
    }

    @Test
    void prioridadBajaDebeSerLaMenosUrgente() {
        assertTrue(
                Prioridad.BAJA.getNivel() > Prioridad.MEDIA.getNivel()
        );
    }
}
