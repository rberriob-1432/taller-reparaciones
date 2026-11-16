package test.estructuras;

import main.estructuras.HashSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashSetTest {

    @Test
    void debeInsertarElemento() {
        HashSet<String> conjunto = new HashSet<>();

        assertTrue(conjunto.add("ABC123"));
        assertEquals(1, conjunto.size());
    }

    @Test
    void noDebePermitirDuplicados() {
        HashSet<String> conjunto = new HashSet<>();

        assertTrue(conjunto.add("ABC123"));
        assertFalse(conjunto.add("ABC123"));

        assertEquals(1, conjunto.size());
    }

    @Test
    void debeBuscarElemento() {
        HashSet<String> conjunto = new HashSet<>();

        conjunto.add("ABC123");

        assertTrue(conjunto.contains("ABC123"));
        assertFalse(conjunto.contains("XYZ999"));
    }

    @Test
    void debeEliminarElemento() {
        HashSet<String> conjunto = new HashSet<>();

        conjunto.add("ABC123");

        assertTrue(conjunto.remove("ABC123"));
        assertFalse(conjunto.contains("ABC123"));
        assertEquals(0, conjunto.size());
    }

    @Test
    void debeRetornarFalseAlEliminarInexistente() {
        HashSet<String> conjunto = new HashSet<>();

        assertFalse(conjunto.remove("ABC123"));
    }

    @Test
    void noDebeAceptarElementosNulos() {
        HashSet<String> conjunto = new HashSet<>();

        assertThrows(IllegalArgumentException.class,
                () -> conjunto.add(null));
    }

    @Test
    void debeResolverColisiones() {
        HashSet<Integer> conjunto = new HashSet<>();

        // 1 y 17 producen el mismo índice con capacidad 16.
        conjunto.add(1);
        conjunto.add(17);

        assertTrue(conjunto.contains(1));
        assertTrue(conjunto.contains(17));
        assertEquals(2, conjunto.size());
    }

    @Test
    void debeRedimensionarTablaHash() {
        HashSet<Integer> conjunto = new HashSet<>();

        for (int i = 0; i < 100; i++) {
            conjunto.add(i);
        }

        assertEquals(100, conjunto.size());

        for (int i = 0; i < 100; i++) {
            assertTrue(conjunto.contains(i));
        }
    }

    @Test
    void debeEliminarElementoEnCadenaDeColisiones() {
        HashSet<Integer> conjunto = new HashSet<>();

        conjunto.add(1);
        conjunto.add(17);
        conjunto.add(33);

        assertTrue(conjunto.remove(17));

        assertTrue(conjunto.contains(1));
        assertTrue(conjunto.contains(33));
        assertFalse(conjunto.contains(17));
    }

    @Test
    void debeReconocerConjuntoVacio() {
        HashSet<String> conjunto = new HashSet<>();

        assertTrue(conjunto.isEmpty());
        assertEquals(0, conjunto.size());
    }
}