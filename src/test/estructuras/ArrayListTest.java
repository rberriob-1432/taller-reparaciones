package test.estructuras;
import main.estructuras.ArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    @Test
    void debeInsertarElementos() {
        ArrayList<String> lista = new ArrayList<>();

        lista.add("Toyota");
        lista.add("Mazda");

        assertEquals(2, lista.size());
        assertEquals("Toyota", lista.get(0));
        assertEquals("Mazda", lista.get(1));
    }

    @Test
    void debeConsultarPorIndice() {
        ArrayList<Integer> lista = new ArrayList<>();

        lista.add(10);
        lista.add(20);

        assertEquals(20, lista.get(1));
    }

    @Test
    void debeModificarElemento() {
        ArrayList<String> lista = new ArrayList<>();

        lista.add("Toyota");
        lista.set(0, "Chevrolet");

        assertEquals("Chevrolet", lista.get(0));
    }

    @Test
    void debeEliminarElemento() {
        ArrayList<Integer> lista = new ArrayList<>();

        lista.add(10);
        lista.add(20);
        lista.add(30);

        assertEquals(20, lista.remove(1));
        assertEquals(2, lista.size());
        assertEquals(30, lista.get(1));
    }

    @Test
    void debeComprobarExistencia() {
        ArrayList<String> lista = new ArrayList<>();

        lista.add("ABC123");

        assertTrue(lista.contains("ABC123"));
        assertFalse(lista.contains("XYZ999"));
    }

    @Test
    void debePermitirElementosNulos() {
        ArrayList<String> lista = new ArrayList<>();

        lista.add(null);

        assertNull(lista.get(0));
        assertTrue(lista.contains(null));
    }

    @Test
    void debeRedimensionarArreglo() {
        ArrayList<Integer> lista = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            lista.add(i);
        }

        assertEquals(25, lista.size());
        assertEquals(24, lista.get(24));
        assertEquals(0, lista.get(0));
    }

    @Test
    void debeRechazarIndiceFueraDeRango() {
        ArrayList<String> lista = new ArrayList<>();

        assertThrows(IndexOutOfBoundsException.class,
                () -> lista.get(0));
    }

    @Test
    void debeRechazarIndiceNegativo() {
        ArrayList<String> lista = new ArrayList<>();

        lista.add("Toyota");

        assertThrows(IndexOutOfBoundsException.class,
                () -> lista.get(-1));
    }

    @Test
    void debeVaciarLista() {
        ArrayList<String> lista = new ArrayList<>();

        lista.add("Toyota");
        lista.add("Mazda");

        lista.clear();

        assertTrue(lista.isEmpty());
        assertEquals(0, lista.size());
    }

    @Test
    void debeEliminarUnicoElemento() {
        ArrayList<Integer> lista = new ArrayList<>();

        lista.add(10);

        assertEquals(10, lista.remove(0));
        assertTrue(lista.isEmpty());
    }
}
