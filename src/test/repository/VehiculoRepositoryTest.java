package test.repository;
import main.model.Vehiculo;
import main.repository.VehiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoRepositoryTest {

    private VehiculoRepository repository;

    @BeforeEach
    void setUp() {
        repository = new VehiculoRepository();
    }

    private Vehiculo crearVehiculo(String placa) {
        return new Vehiculo(
                placa, "Toyota", "Corolla", 2022, "Gris"
        );
    }

    @Test
    void debeRegistrarVehiculo() {
        repository.registrar(crearVehiculo("ABC123"));

        assertEquals(1, repository.cantidad());
    }

    @Test
    void debeBuscarVehiculoPorPlaca() {
        Vehiculo vehiculo = crearVehiculo("ABC123");
        repository.registrar(vehiculo);

        assertEquals(vehiculo, repository.buscarPorPlaca("ABC123"));
    }

    @Test
    void noDebeRegistrarPlacasDuplicadas() {
        repository.registrar(crearVehiculo("ABC123"));

        assertThrows(IllegalArgumentException.class, () ->
                repository.registrar(crearVehiculo("ABC123"))
        );
    }

    @Test
    void debeBuscarPlacaSinImportarMayusculas() {
        repository.registrar(crearVehiculo("ABC123"));

        assertTrue(repository.existe("abc123"));
    }

    @Test
    void debeRetornarNullCuandoNoExisteVehiculo() {
        assertNull(repository.buscarPorPlaca("XYZ999"));
    }

    @Test
    void debeActualizarVehiculo() {
        repository.registrar(crearVehiculo("ABC123"));

        Vehiculo actualizado = new Vehiculo(
                "ABC123", "Mazda", "CX5", 2023, "Negro"
        );

        assertTrue(repository.actualizar(actualizado));
        assertEquals("Mazda",
                repository.buscarPorPlaca("ABC123").getMarca());
    }

    @Test
    void noDebeActualizarVehiculoInexistente() {
        assertFalse(repository.actualizar(crearVehiculo("ABC123")));
    }

    @Test
    void debeEliminarVehiculo() {
        repository.registrar(crearVehiculo("ABC123"));

        assertTrue(repository.eliminar("ABC123"));
        assertEquals(0, repository.cantidad());
    }

    @Test
    void noDebeEliminarVehiculoInexistente() {
        assertFalse(repository.eliminar("XYZ999"));
    }

    @Test
    void debeIndicarSiEstaVacio() {
        assertTrue(repository.estaVacio());

        repository.registrar(crearVehiculo("ABC123"));

        assertFalse(repository.estaVacio());
    }
}
