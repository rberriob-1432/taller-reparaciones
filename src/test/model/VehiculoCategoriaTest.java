package test.model;
import main.model.VehiculoCategoria;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehiculoCategoriaTest {

    @Test
    void debeContenerTodasLasCategorias() {
        assertEquals(6, VehiculoCategoria.values().length);
    }

    @Test
    void debeContenerCategoriaAutomovil() {
        assertNotNull(VehiculoCategoria.valueOf("AUTOMOVIL"));
    }

    @Test
    void debeContenerCategoriaMotocicleta() {
        assertNotNull(VehiculoCategoria.valueOf("MOTOCICLETA"));
    }

    @Test
    void debeContenerCategoriaCamioneta() {
        assertNotNull(VehiculoCategoria.valueOf("CAMIONETA"));
    }

    @Test
    void debeContenerCategoriaCamion() {
        assertNotNull(VehiculoCategoria.valueOf("CAMION"));
    }

    @Test
    void debeContenerCategoriaBus() {
        assertNotNull(VehiculoCategoria.valueOf("BUS"));
    }

    @Test
    void debeContenerCategoriaVan() {
        assertNotNull(VehiculoCategoria.valueOf("VAN"));
    }
}
