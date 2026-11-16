package test.service;
import main.model.Vehiculo;
import main.repository.VehiculoRepository;
import main.service.VehiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoServiceTest {

    private VehiculoRepository repository;
    private VehiculoService service;

    @BeforeEach
    void setUp() {
        repository = new VehiculoRepository();
        service = new VehiculoService(repository);
    }

    private Vehiculo crearVehiculo(String placa) {
        return new Vehiculo(
                placa, "Toyota", "Corolla", 2022, "Gris"
        );
    }

    @Test
    void debeCrearServicioConRepositorioValido() {
        assertNotNull(service);
    }

    @Test
    void noDebeCrearServicioConRepositorioNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new VehiculoService(null));
    }

    @Test
    void debeRegistrarVehiculo() {
        service.registrar(crearVehiculo("ABC123"));

        assertEquals(1, service.cantidad());
    }

    @Test
    void debeBuscarVehiculoPorPlaca() {
        Vehiculo vehiculo = crearVehiculo("ABC123");
        service.registrar(vehiculo);

        assertEquals(vehiculo, service.buscarPorPlaca("ABC123"));
    }

    @Test
    void debeRetornarNullSiVehiculoNoExiste() {
        assertNull(service.buscarPorPlaca("XYZ999"));
    }

    @Test
    void debeActualizarVehiculo() {
        service.registrar(crearVehiculo("ABC123"));

        Vehiculo actualizado = new Vehiculo(
                "ABC123", "Mazda", "CX5", 2023, "Negro"
        );

        assertTrue(service.actualizar(actualizado));
        assertEquals("Mazda",
                service.buscarPorPlaca("ABC123").getMarca());
    }

    @Test
    void noDebeActualizarVehiculoInexistente() {
        assertFalse(service.actualizar(crearVehiculo("ABC123")));
    }

    @Test
    void debeEliminarVehiculo() {
        service.registrar(crearVehiculo("ABC123"));

        assertTrue(service.eliminar("ABC123"));
        assertEquals(0, service.cantidad());
    }

    @Test
    void noDebeEliminarVehiculoInexistente() {
        assertFalse(service.eliminar("XYZ999"));
    }

    @Test
    void debeComprobarSiVehiculoExiste() {
        service.registrar(crearVehiculo("ABC123"));

        assertTrue(service.existe("ABC123"));
        assertFalse(service.existe("XYZ999"));
    }

    @Test
    void debeRetornarCantidadDeVehiculos() {
        service.registrar(crearVehiculo("ABC123"));
        service.registrar(crearVehiculo("DEF456"));

        assertEquals(2, service.cantidad());
    }
}
