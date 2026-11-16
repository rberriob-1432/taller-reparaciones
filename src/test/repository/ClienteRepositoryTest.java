package test.repository;
import main.model.Cliente;
import main.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteRepositoryTest {

    private ClienteRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ClienteRepository();
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
    void debeRegistrarCliente() {
        repository.registrar(crearCliente("1001"));

        assertEquals(1, repository.cantidad());
    }

    @Test
    void debeBuscarClientePorIdentificacion() {
        Cliente cliente = crearCliente("1001");
        repository.registrar(cliente);

        assertEquals(cliente, repository.buscarPorIdentificacion("1001"));
    }

    @Test
    void noDebeRegistrarIdentificacionesDuplicadas() {
        repository.registrar(crearCliente("1001"));

        assertThrows(IllegalArgumentException.class, () ->
                repository.registrar(crearCliente("1001"))
        );
    }

    @Test
    void debeRetornarNullCuandoNoExisteCliente() {
        assertNull(repository.buscarPorIdentificacion("9999"));
    }

    @Test
    void debeActualizarCliente() {
        repository.registrar(crearCliente("1001"));

        Cliente actualizado = new Cliente(
                "1001", "Maria Lopez", "3111111111", "maria@email.com"
        );

        assertTrue(repository.actualizar(actualizado));
        assertEquals("Maria Lopez",
                repository.buscarPorIdentificacion("1001").getNombre());
    }

    @Test
    void noDebeActualizarClienteInexistente() {
        assertFalse(repository.actualizar(crearCliente("1001")));
    }

    @Test
    void debeEliminarCliente() {
        repository.registrar(crearCliente("1001"));

        assertTrue(repository.eliminar("1001"));
        assertEquals(0, repository.cantidad());
    }

    @Test
    void noDebeEliminarClienteInexistente() {
        assertFalse(repository.eliminar("9999"));
    }

    @Test
    void debeIndicarSiEstaVacio() {
        assertTrue(repository.estaVacio());

        repository.registrar(crearCliente("1001"));

        assertFalse(repository.estaVacio());
    }
}
