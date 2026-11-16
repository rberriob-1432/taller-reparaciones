package test.estructuras;

import main.estructuras.LinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    void debeInsertarAlFinal() {
        LinkedList<String> lista = new LinkedList<>();

        lista.add("Toyota");
        lista.add("Mazda");

        assertEquals(2, lista.size());
        assertEquals("Toyota", lista.getFirst());
        assertEquals("Mazda", lista.getLast());
    }

    @Test
    void debeInsertarAlInicio() {
        LinkedList<Integer> lista = new LinkedList<>();

        lista.add(20);
        lista.addFirst(10);

        assertEquals(10, lista.getFirst());
        assertEquals(20, lista.getLast());
    }

    @Test
    void debeConsultarPrimerElemento() {
        LinkedList<String> lista = new LinkedList<>();

        lista.add("ABC123");

        assertEquals("ABC123", lista.getFirst());
    }

    @Test
    void debeConsultarUltimoElemento() {
        LinkedList<String> lista = new LinkedList<>();

        lista.add("ABC123");
        lista.add("XYZ999");

        assertEquals("XYZ999", lista.getLast());
    }

    @Test
    void debeEliminarPrimerElemento() {
        LinkedList<Integer> lista = new LinkedList<>();

        lista.add(10);
        lista.add(20);
        lista.add(30);

        assertTrue(lista.remove(10));
        assertEquals(20, lista.getFirst());
        assertEquals(2, lista.size());
    }

    @Test
    void debeEliminarUltimoElemento() {
        LinkedList<Integer> lista = new LinkedList<>();

        lista.add(10);
        lista.add(20);

        assertTrue(lista.remove(20));
        assertEquals(10, lista.getLast());
        assertEquals(1, lista.size());
    }

    @Test
    void debeEliminarElementoDelMedio() {
        LinkedList<Integer> lista = new LinkedList<>();

        lista.add(10);
        lista.add(20);
        lista.add(30);

        assertTrue(lista.remove(20));
        assertEquals(2, lista.size());
        assertFalse(lista.contains(20));
    }

    @Test
    void debeRetornarFalseCuandoNoExiste() {
        LinkedList<Integer> lista = new LinkedList<>();

        lista.add(10);

        assertFalse(lista.remove(99));
    }

    @Test
    void debeBuscarElementos() {
        LinkedList<String> lista = new LinkedList<>();

        lista.add("ABC123");

        assertTrue(lista.contains("ABC123"));
        assertFalse(lista.contains("XYZ999"));
    }

    @Test
    void debeEliminarUltimoNodoYActualizarCola() {
        LinkedList<Integer> lista = new LinkedList<>();

        lista.add(10);

        assertTrue(lista.remove(10));
        assertTrue(lista.isEmpty());

        lista.add(20);

        assertEquals(20, lista.getFirst());
        assertEquals(20, lista.getLast());
    }

    @Test
    void debePermitirElementosNulos() {
        LinkedList<String> lista = new LinkedList<>();

        lista.add(null);

        assertTrue(lista.contains(null));
        assertNull(lista.getFirst());
    }

    @Test
    void debeLanzarExcepcionAlConsultarListaVacia() {
        LinkedList<String> lista = new LinkedList<>();

        assertThrows(IllegalStateException.class,
                lista::getFirst);

        assertThrows(IllegalStateException.class,
                lista::getLast);
    }

    @Test
    void debeVaciarLista() {
        LinkedList<Integer> lista = new LinkedList<>();

        lista.add(10);
        lista.add(20);

        lista.clear();

        assertTrue(lista.isEmpty());
        assertEquals(0, lista.size());
    }
}
