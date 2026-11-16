package test.model;
import main.model.Cliente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void debeCrearClienteCorrectamente() {
        Cliente cliente = new Cliente(
                "123456789",
                "Carlos Perez",
                "3001234567",
                "carlos@gmail.com"
        );

        assertEquals("123456789", cliente.getIdentificacion());
        assertEquals("Carlos Perez", cliente.getNombre());
        assertEquals("3001234567", cliente.getTelefono());
        assertEquals("carlos@gmail.com", cliente.getCorreo());
    }

    @Test
    void debeLimpiarEspaciosDeIdentificacion() {
        Cliente cliente = new Cliente(
                " 123456789 ",
                "Carlos Perez",
                "3001234567",
                "carlos@gmail.com"
        );

        assertEquals("123456789", cliente.getIdentificacion());
    }

    @Test
    void noDebeAceptarIdentificacionVacia() {
        assertThrows(IllegalArgumentException.class, () ->
                new Cliente("", "Carlos Perez", "3001234567", "correo@gmail.com")
        );
    }

    @Test
    void noDebeAceptarIdentificacionNula() {
        assertThrows(IllegalArgumentException.class, () ->
                new Cliente(null, "Carlos Perez", "3001234567", "correo@gmail.com")
        );
    }

    @Test
    void noDebeAceptarNombreVacio() {
        assertThrows(IllegalArgumentException.class, () ->
                new Cliente("123456789", " ", "3001234567", "correo@gmail.com")
        );
    }

    @Test
    void debePermitirModificarTelefono() {
        Cliente cliente = new Cliente(
                "123456789", "Carlos Perez", "3001234567", "correo@gmail.com"
        );

        cliente.setTelefono("3109876543");

        assertEquals("3109876543", cliente.getTelefono());
    }

    @Test
    void debePermitirModificarCorreo() {
        Cliente cliente = new Cliente(
                "123456789", "Carlos Perez", "3001234567", "correo@gmail.com"
        );

        cliente.setCorreo("nuevo@gmail.com");

        assertEquals("nuevo@gmail.com", cliente.getCorreo());
    }

    @Test
    void debeGenerarRepresentacionTextual() {
        Cliente cliente = new Cliente(
                "123456789", "Carlos Perez", "3001234567", "correo@gmail.com"
        );

        assertTrue(cliente.toString().contains("Carlos Perez"));
        assertTrue(cliente.toString().contains("123456789"));
    }
}
