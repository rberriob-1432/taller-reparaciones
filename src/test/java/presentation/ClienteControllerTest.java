package test.java.presentation;
import main.java.domain.model.Cliente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ClienteControllerTest {

    @Test
    void debeCrearClienteConDatosValidos() {
        Cliente cliente = new Cliente("123", "Raul Berrio", "3001234567");

        assertEquals("123", cliente.getIdentificacion());
        assertEquals("Raul Berrio", cliente.getNombre());
        assertEquals("3001234567", cliente.getTelefono());
    }

    @Test
    void debeEliminarEspaciosDeLosDatos() {
        Cliente cliente = new Cliente(" 123 ", " Raul ", " 300123 ");

        assertEquals("123", cliente.getIdentificacion());
        assertEquals("Raul", cliente.getNombre());
        assertEquals("300123", cliente.getTelefono());
    }

    @Test
    void debeRechazarIdentificacionVacia() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cliente(" ", "Raul", "300123"));
    }

    @Test
    void debeRechazarNombreNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cliente("123", null, "300123"));
    }

    @Test
    void debeRechazarTelefonoVacio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cliente("123", "Raul", " "));
    }

    @Test
    void dosClientesConLaMismaIdentificacionDebenSerIguales() {
        Cliente primero = new Cliente("123", "Raul", "300111");
        Cliente segundo = new Cliente("123", "Carlos", "300222");

        assertEquals(primero, segundo);
        assertEquals(primero.hashCode(), segundo.hashCode());
    }
}