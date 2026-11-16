package test.service;
import main.model.Cliente;
import main.repository.ClienteRepository;
import main.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceTest {

    private ClienteRepository repository;
    private ClienteService service;

    @BeforeEach
    void setUp() {
        repository = new ClienteRepository();
        service = new ClienteService(repository);
    }

    private Cliente crearCliente(String identificacion) {
        return new Cliente(
                identificacion,
                "Carlos Perez",
                "3001234567",
                "carlos@email.com"
        );
    }

    @Test
    void debeCrearServicioConRepositorioValido() {
        assertNotNull(service);
    }

    @Test
    void noDebeCrearServicioConRepositorioNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new ClienteService(null));
    }

    @Test
    void debeRegistrarCliente() {
        service.registrar(crearCliente("1001"));

        assertEquals(1, service.cantidad());
    }

    @Test
    void debeBuscarClientePorIdentificacion() {
        Cliente cliente = crearCliente("1001");
        service.registrar(cliente);

        assertEquals(cliente,
                service.buscarPorIdentificacion("1001"));
    }

    @Test
    void debeRetornarNullSiClienteNoExiste() {
        assertNull(service.buscarPorIdentificacion("9999"));
    }

    @Test
    void debeActualizarCliente() {
        service.registrar(crearCliente("1001"));

        Cliente actualizado = new Cliente(
                "1001", "Maria Lopez", "3111111111", "maria@email.com"
        );

        assertTrue(service.actualizar(actualizado));
        assertEquals("Maria Lopez",
                service.buscarPorIdentificacion("1001").getNombre());
    }

    @Test
    void noDebeActualizarClienteInexistente() {
        assertFalse(service.actualizar(crearCliente("1001")));
    }

    @Test
    void debeEliminarCliente() {
        service.registrar(crearCliente("1001"));

        assertTrue(service.eliminar("1001"));
        assertEquals(0, service.cantidad());
    }

    @Test
    void noDebeEliminarClienteInexistente() {
        assertFalse(service.eliminar("9999"));
    }

    @Test
    void debeComprobarSiClienteExiste() {
        service.registrar(crearCliente("1001"));

        assertTrue(service.existe("1001"));
        assertFalse(service.existe("9999"));
    }

    @Test
    void debeRetornarCantidadDeClientes() {
        service.registrar(crearCliente("1001"));
        service.registrar(crearCliente("1002"));

        assertEquals(2, service.cantidad());
    }
}
