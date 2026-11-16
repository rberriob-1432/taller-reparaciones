package test.model;
import main.model.Vehiculo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehiculoTest {

    @Test
    void debeCrearVehiculoCorrectamente() {
        Vehiculo vehiculo = new Vehiculo(
                "abc123", "Toyota", "Corolla", 2024, "Gris"
        );

        assertEquals("ABC123", vehiculo.getPlaca());
        assertEquals("Toyota", vehiculo.getMarca());
        assertEquals("Corolla", vehiculo.getModelo());
        assertEquals(2024, vehiculo.getAnio());
        assertEquals("Gris", vehiculo.getColor());
    }

    @Test
    void debeConvertirPlacaAMayusculas() {
        Vehiculo vehiculo = new Vehiculo(
                " abc123 ", "Toyota", "Corolla", 2024, "Gris"
        );

        assertEquals("ABC123", vehiculo.getPlaca());
    }

    @Test
    void noDebeAceptarPlacaVacia() {
        assertThrows(IllegalArgumentException.class, () ->
                new Vehiculo("", "Toyota", "Corolla", 2024, "Gris")
        );
    }

    @Test
    void noDebeAceptarPlacaNula() {
        assertThrows(IllegalArgumentException.class, () ->
                new Vehiculo(null, "Toyota", "Corolla", 2024, "Gris")
        );
    }

    @Test
    void noDebeAceptarMarcaVacia() {
        assertThrows(IllegalArgumentException.class, () ->
                new Vehiculo("ABC123", " ", "Corolla", 2024, "Gris")
        );
    }

    @Test
    void noDebeAceptarModeloVacio() {
        assertThrows(IllegalArgumentException.class, () ->
                new Vehiculo("ABC123", "Toyota", "", 2024, "Gris")
        );
    }

    @Test
    void noDebeAceptarAnioInvalido() {
        assertThrows(IllegalArgumentException.class, () ->
                new Vehiculo("ABC123", "Toyota", "Corolla", 0, "Gris")
        );
    }

    @Test
    void debePermitirModificarColor() {
        Vehiculo vehiculo = new Vehiculo(
                "ABC123", "Toyota", "Corolla", 2024, "Gris"
        );

        vehiculo.setColor("Negro");

        assertEquals("Negro", vehiculo.getColor());
    }

    @Test
    void debeGenerarRepresentacionTextual() {
        Vehiculo vehiculo = new Vehiculo(
                "ABC123", "Toyota", "Corolla", 2024, "Gris"
        );

        assertTrue(vehiculo.toString().contains("ABC123"));
        assertTrue(vehiculo.toString().contains("Toyota"));
    }
}
