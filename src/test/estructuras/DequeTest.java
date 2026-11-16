package test.estructuras;
import main.estructuras.Deque;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DequeTest {

    @Test
    void debeInsertarAlInicio() {
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(20);
        deque.addFirst(10);

        assertEquals(10, deque.peekFirst());
        assertEquals(2, deque.size());
    }

    @Test
    void debeInsertarAlFinal() {
        Deque<Integer> deque = new Deque<>();

        deque.addLast(10);
        deque.addLast(20);

        assertEquals(20, deque.peekLast());
        assertEquals(2, deque.size());
    }

    @Test
    void debeEliminarDesdeElInicio() {
        Deque<Integer> deque = new Deque<>();

        deque.addLast(10);
        deque.addLast(20);

        assertEquals(10, deque.removeFirst());
        assertEquals(20, deque.peekFirst());
    }

    @Test
    void debeEliminarDesdeElFinal() {
        Deque<Integer> deque = new Deque<>();

        deque.addLast(10);
        deque.addLast(20);

        assertEquals(20, deque.removeLast());
        assertEquals(10, deque.peekLast());
    }

    @Test
    void debePermitirOperacionesEnAmbosExtremos() {
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(20);
        deque.addLast(30);
        deque.addFirst(10);

        assertEquals(10, deque.removeFirst());
        assertEquals(30, deque.removeLast());
        assertEquals(20, deque.peekFirst());
    }

    @Test
    void debeRetornarNullCuandoEstaVacio() {
        Deque<Integer> deque = new Deque<>();

        assertNull(deque.removeFirst());
        assertNull(deque.removeLast());
        assertNull(deque.peekFirst());
        assertNull(deque.peekLast());
    }

    @Test
    void noDebeAceptarElementosNulos() {
        Deque<String> deque = new Deque<>();

        assertThrows(IllegalArgumentException.class,
                () -> deque.addFirst(null));

        assertThrows(IllegalArgumentException.class,
                () -> deque.addLast(null));
    }

    @Test
    void debeActualizarExtremosAlEliminarUltimoElemento() {
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(10);

        assertEquals(10, deque.removeFirst());
        assertTrue(deque.isEmpty());

        deque.addLast(20);

        assertEquals(20, deque.peekFirst());
        assertEquals(20, deque.peekLast());
    }

    @Test
    void debeVaciarDeque() {
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(10);
        deque.addLast(20);

        deque.clear();

        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }
}
