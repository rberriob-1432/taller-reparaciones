package test.estructuras;
import main.estructuras.PriorityQueue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {

    @Test
    void debeInsertarElementos() {
        PriorityQueue<Integer> cola = new PriorityQueue<>();

        cola.offer(30);
        cola.offer(10);
        cola.offer(20);

        assertEquals(3, cola.size());
    }

    @Test
    void debeMantenerElMenorElementoEnLaRaiz() {
        PriorityQueue<Integer> cola = new PriorityQueue<>();

        cola.offer(30);
        cola.offer(10);
        cola.offer(20);

        assertEquals(10, cola.peek());
    }

    @Test
    void debeExtraerElementosEnOrdenAscendente() {
        PriorityQueue<Integer> cola = new PriorityQueue<>();

        cola.offer(40);
        cola.offer(10);
        cola.offer(30);
        cola.offer(20);

        assertEquals(10, cola.poll());
        assertEquals(20, cola.poll());
        assertEquals(30, cola.poll());
        assertEquals(40, cola.poll());
    }

    @Test
    void debeReorganizarHeapDespuesDeEliminarRaiz() {
        PriorityQueue<Integer> cola = new PriorityQueue<>();

        cola.offer(10);
        cola.offer(20);
        cola.offer(30);
        cola.offer(40);

        assertEquals(10, cola.poll());
        assertEquals(20, cola.peek());
        assertEquals(20, cola.poll());
    }

    @Test
    void debeConsultarRaizSinEliminar() {
        PriorityQueue<Integer> cola = new PriorityQueue<>();

        cola.offer(5);
        cola.offer(10);

        assertEquals(5, cola.peek());
        assertEquals(2, cola.size());
    }

    @Test
    void debePermitirElementosRepetidos() {
        PriorityQueue<Integer> cola = new PriorityQueue<>();

        cola.offer(10);
        cola.offer(10);
        cola.offer(20);

        assertEquals(3, cola.size());

        assertEquals(10, cola.poll());
        assertEquals(10, cola.poll());
        assertEquals(20, cola.poll());
    }

    @Test
    void debeRetornarNullCuandoEstaVacia() {
        PriorityQueue<Integer> cola = new PriorityQueue<>();

        assertNull(cola.peek());
        assertNull(cola.poll());
    }

    @Test
    void noDebeAceptarElementosNulos() {
        PriorityQueue<Integer> cola = new PriorityQueue<>();

        assertThrows(IllegalArgumentException.class,
                () -> cola.offer(null));
    }

    @Test
    void debeRedimensionarArreglo() {
        PriorityQueue<Integer> cola = new PriorityQueue<>();

        for (int i = 100; i >= 1; i--) {
            cola.offer(i);
        }

        assertEquals(100, cola.size());
        assertEquals(1, cola.peek());

        for (int i = 1; i <= 100; i++) {
            assertEquals(i, cola.poll());
        }

        assertTrue(cola.isEmpty());
    }

    @Test
    void debeVaciarColaDePrioridad() {
        PriorityQueue<Integer> cola = new PriorityQueue<>();

        cola.offer(10);
        cola.offer(20);

        cola.clear();

        assertTrue(cola.isEmpty());
        assertEquals(0, cola.size());
        assertNull(cola.poll());
    }

    @Test
    void debeMantenerOrdenConNumerosNegativos() {
        PriorityQueue<Integer> cola = new PriorityQueue<>();

        cola.offer(5);
        cola.offer(-10);
        cola.offer(0);

        assertEquals(-10, cola.poll());
        assertEquals(0, cola.poll());
        assertEquals(5, cola.poll());
    }
}
