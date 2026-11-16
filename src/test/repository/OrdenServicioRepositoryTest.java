package test.repository;
import main.model.*;
import main.repository.OrdenServicioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrdenServicioRepositoryTest {

    private OrdenServicioRepository repository;
    private Vehiculo vehiculo;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        repository = new OrdenServicioRepository();

        vehiculo = new Vehiculo(
                "ABC123", "Toyota", "Corolla", 2022, "Gris"
        );

        cliente = new Cliente(
                "1001", "Carlos Perez", "3001234567", "carlos@email.com"
        );
    }

    private OrdenServicio crearOrden(String codigo, Prioridad prioridad) {
        return new OrdenServicio(
                codigo,
                "Falla en el motor",
                TipoServicio.REPARACION_MOTOR,
                prioridad,
                vehiculo,
                cliente
        );
    }

    @Test
    void debeRegistrarOrden() {
        repository.registrar(crearOrden("ORD001", Prioridad.ALTA));

        assertEquals(1, repository.cantidad());
    }

    @Test
    void debeBuscarOrdenPorCodigo() {
        OrdenServicio orden = crearOrden("ORD001", Prioridad.ALTA);
        repository.registrar(orden);

        assertEquals(orden, repository.buscarPorCodigo("ORD001"));
    }

    @Test
    void noDebeRegistrarCodigosDuplicados() {
        repository.registrar(crearOrden("ORD001", Prioridad.ALTA));

        assertThrows(IllegalArgumentException.class, () ->
                repository.registrar(crearOrden("ORD001", Prioridad.BAJA))
        );
    }

    @Test
    void debeIndicarSiExisteUnaOrden() {
        repository.registrar(crearOrden("ORD001", Prioridad.ALTA));

        assertTrue(repository.existe("ORD001"));
        assertFalse(repository.existe("ORD999"));
    }

    @Test
    void debeRetornarNullCuandoNoExisteOrden() {
        assertNull(repository.buscarPorCodigo("ORD999"));
    }

    @Test
    void debeActualizarOrden() {
        repository.registrar(crearOrden("ORD001", Prioridad.BAJA));

        OrdenServicio actualizada = crearOrden("ORD001", Prioridad.CRITICA);

        assertTrue(repository.actualizar(actualizada));
        assertEquals(
                Prioridad.CRITICA,
                repository.buscarPorCodigo("ORD001").getPrioridad()
        );
    }

    @Test
    void noDebeActualizarOrdenInexistente() {
        assertFalse(repository.actualizar(
                crearOrden("ORD999", Prioridad.ALTA)
        ));
    }

    @Test
    void debeEliminarOrden() {
        repository.registrar(crearOrden("ORD001", Prioridad.ALTA));

        assertTrue(repository.eliminar("ORD001"));
        assertEquals(0, repository.cantidad());
    }

    @Test
    void noDebeEliminarOrdenInexistente() {
        assertFalse(repository.eliminar("ORD999"));
    }

    @Test
    void debeListarOrdenesPorPrioridad() {
        repository.registrar(crearOrden("ORD001", Prioridad.BAJA));
        repository.registrar(crearOrden("ORD002", Prioridad.CRITICA));
        repository.registrar(crearOrden("ORD003", Prioridad.MEDIA));
        repository.registrar(crearOrden("ORD004", Prioridad.ALTA));

        var resultado = repository.listarPorPrioridad();

        assertEquals(4, resultado.size());
        assertEquals("ORD002", resultado.get(0).getCodigo());
        assertEquals("ORD004", resultado.get(1).getCodigo());
        assertEquals("ORD003", resultado.get(2).getCodigo());
        assertEquals("ORD001", resultado.get(3).getCodigo());
    }

    @Test
    void noDebeIncluirOrdenesFinalizadasEnPrioridad() {
        OrdenServicio orden = crearOrden("ORD001", Prioridad.CRITICA);
        orden.setEstado(EstadoOrden.FINALIZADA);

        repository.registrar(orden);
        repository.registrar(crearOrden("ORD002", Prioridad.BAJA));

        var resultado = repository.listarPorPrioridad();

        assertEquals(1, resultado.size());
        assertEquals("ORD002", resultado.get(0).getCodigo());
    }

    @Test
    void noDebeIncluirOrdenesCanceladasEnPrioridad() {
        OrdenServicio orden = crearOrden("ORD001", Prioridad.CRITICA);
        orden.setEstado(EstadoOrden.CANCELADA);

        repository.registrar(orden);

        assertTrue(repository.listarPorPrioridad().isEmpty());
    }

    @Test
    void debeIndicarSiEstaVacio() {
        assertTrue(repository.estaVacio());

        repository.registrar(crearOrden("ORD001", Prioridad.ALTA));

        assertFalse(repository.estaVacio());
    }
}
