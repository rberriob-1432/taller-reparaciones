package test.estructuras;
import main.estructuras.TreeSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeSetTest {

    @Test
    void debeInsertarElementos() {
        TreeSet<Integer> conjunto = new TreeSet<>();

        assertTrue(conjunto.add(50));
        assertTrue(conjunto.add(30));
        assertTrue(conjunto.add(70));

        assertEquals(3, conjunto.size());
    }

    @Test
    void noDebePermitirDuplicados() {
        TreeSet<Integer> conjunto = new TreeSet<>();

        assertTrue(conjunto.add(50));
        assertFalse(conjunto.add(50));

        assertEquals(1, conjunto.size());
    }

    @Test
    void debeBuscarElementos() {
        TreeSet<Integer> conjunto = new TreeSet<>();

        conjunto.add(50);
        conjunto.add(30);
        conjunto.add(70);

        assertTrue(conjunto.contains(30));
        assertTrue(conjunto.contains(70));
        assertFalse(conjunto.contains(90));
    }

    @Test
    void debeMantenerElMenorElemento() {
        TreeSet<Integer> conjunto = new TreeSet<>();

        conjunto.add(50);
        conjunto.add(20);
        conjunto.add(70);

        assertEquals(20, conjunto.first());
    }

    @Test
    void debeEliminarHoja() {
        TreeSet<Integer> conjunto = new TreeSet<>();

        conjunto.add(50);
        conjunto.add(30);
        conjunto.add(70);

        assertTrue(conjunto.remove(30));
        assertFalse(conjunto.contains(30));
        assertEquals(2, conjunto.size());
    }

    @Test
    void debeEliminarNodoConUnHijo() {
        TreeSet<Integer> conjunto = new TreeSet<>();

        conjunto.add(50);
        conjunto.add(30);
        conjunto.add(20);

        assertTrue(conjunto.remove(30));
        assertTrue(conjunto.contains(20));
        assertEquals(2, conjunto.size());
    }

    @Test
    void debeEliminarNodoConDosHijos() {
        TreeSet<Integer> conjunto = new TreeSet<>();

        conjunto.add(50);
        conjunto.add(30);
        conjunto.add(70);
        conjunto.add(20);
        conjunto.add(40);

        assertTrue(conjunto.remove(30));

        assertFalse(conjunto.contains(30));
        assertTrue(conjunto.contains(20));
        assertTrue(conjunto.contains(40));
        assertEquals(4, conjunto.size());
    }

    @Test
    void debeRetornarFalseAlEliminarInexistente() {
        TreeSet<Integer> conjunto = new TreeSet<>();

        assertFalse(conjunto.remove(100));
    }

    @Test
    void noDebeAceptarElementosNulos() {
        TreeSet<Integer> conjunto = new TreeSet<>();

        assertThrows(IllegalArgumentException.class,
                () -> conjunto.add(null));
    }

    @Test
    void debeLanzarExcepcionAlConsultarArbolVacio() {
        TreeSet<Integer> conjunto = new TreeSet<>();

        assertThrows(IllegalStateException.class,
                conjunto::first);
    }

    @Test
    void debeEliminarLaRaiz() {
        TreeSet<Integer> conjunto = new TreeSet<>();

        conjunto.add(50);
        conjunto.add(30);
        conjunto.add(70);

        assertTrue(conjunto.remove(50));

        assertFalse(conjunto.contains(50));
        assertTrue(conjunto.contains(30));
        assertTrue(conjunto.contains(70));
        assertEquals(2, conjunto.size());
    }
}
