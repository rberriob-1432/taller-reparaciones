package test.estructuras;
import main.estructuras.Queue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void debeInsertarElementos() {
        Queue<String> cola = new Queue<>();

        cola.offer("Toyota");
        cola.offer("Mazda");

        assertEquals(2, cola.size());
    }

    @Test
    void debeRespetarOrdenFIFO() {
        Queue<Integer> cola = new Queue<>();

        cola.offer(10);
        cola.offer(20);
        cola.offer(30);

        assertEquals(10, cola.poll());
        assertEquals(20, cola.poll());
        assertEquals(30, cola.poll());
    }

    @Test
    void debeConsultarFrenteSinEliminar() {
        Queue<String> cola = new Queue<>();

        cola.offer("ABC123");

        assertEquals("ABC123", cola.peek());
        assertEquals(1, cola.size());
    }

    @Test
    void debeEliminarElementoDelFrente() {
        Queue<Integer> cola = new Queue<>();

        cola.offer(10);
        cola.offer(20);

        assertEquals(10, cola.poll());
        assertEquals(20, cola.peek());
    }

    @Test
    void debeRetornarNullCuandoColaEstaVacia() {
        Queue<String> cola = new Queue<>();

        assertNull(cola.poll());
        assertNull(cola.peek());
    }

    @Test
    void noDebeAceptarElementosNulos() {
        Queue<String> cola = new Queue<>();

        assertThrows(IllegalArgumentException.class,
                () -> cola.offer(null));
    }

    @Test
    void debeActualizarFrenteYFinalAlVaciarCola() {
        Queue<Integer> cola = new Queue<>();

        cola.offer(10);

        assertEquals(10, cola.poll());
        assertTrue(cola.isEmpty());

        cola.offer(20);

        assertEquals(20, cola.peek());
        assertEquals(20, cola.poll());
    }

    @Test
    void debeVaciarCola() {
        Queue<String> cola = new Queue<>();

        cola.offer("Toyota");
        cola.offer("Mazda");

        cola.clear();

        assertTrue(cola.isEmpty());
        assertEquals(0, cola.size());
        assertNull(cola.poll());
    }
}
