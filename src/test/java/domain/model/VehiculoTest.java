package test.java.domain.model;
import main.java.domain.model.Cliente;
import main.java.domain.model.Vehiculo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehiculoTest {

    private Cliente crearCliente() {
        return new Cliente("123", "Raul Berrio", "3001234567");
    }

    @Test
    void debeCrearVehiculoConDatosValidos() {
        Vehiculo vehiculo = new Vehiculo(
                "abc123", "Toyota", "Corolla", crearCliente()
        );

        assertEquals("ABC123", vehiculo.getPlaca());
        assertEquals("Toyota", vehiculo.getMarca());
        assertEquals("Corolla", vehiculo.getModelo());
        assertNotNull(vehiculo.getPropietario());
    }

    @Test
    void debeNormalizarLaPlaca() {
        Vehiculo vehiculo = new Vehiculo(
                " abc123 ", "Toyota", "Corolla", crearCliente()
        );

        assertEquals("ABC123", vehiculo.getPlaca());
    }

    @Test
    void debeRechazarPlacaVacia() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vehiculo(" ", "Toyota", "Corolla", crearCliente()));
    }

    @Test
    void debeRechazarMarcaVacia() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vehiculo("ABC123", " ", "Corolla", crearCliente()));
    }

    @Test
    void debeRechazarModeloNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vehiculo("ABC123", "Toyota", null, crearCliente()));
    }

    @Test
    void debeRechazarPropietarioNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vehiculo("ABC123", "Toyota", "Corolla", null));
    }

    @Test
    void dosVehiculosConLaMismaPlacaDebenSerIguales() {
        Vehiculo primero = new Vehiculo(
                "ABC123", "Toyota", "Corolla", crearCliente()
        );

        Vehiculo segundo = new Vehiculo(
                "ABC123", "Mazda", "3", crearCliente()
        );

        assertEquals(primero, segundo);
        assertEquals(primero.hashCode(), segundo.hashCode());
    }
}