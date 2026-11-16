package test.estructuras;
import main.estructuras.TreeMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TreeMapTest {

    @Test
    void debeAlmacenarClaveYValor() {
        TreeMap<Integer, String> mapa = new TreeMap<>();

        mapa.put(100, "Orden 100");

        assertEquals("Orden 100", mapa.get(100));
        assertEquals(1, mapa.size());
    }

    @Test
    void debeActualizarValorDeClaveExistente() {
        TreeMap<Integer, String> mapa = new TreeMap<>();

        mapa.put(100, "Pendiente");
        String anterior = mapa.put(100, "Finalizada");

        assertEquals("Pendiente", anterior);
        assertEquals("Finalizada", mapa.get(100));
        assertEquals(1, mapa.size());
    }

    @Test
    void debeBuscarClaves() {
        TreeMap<Integer, String> mapa = new TreeMap<>();

        mapa.put(100, "Orden 100");
        mapa.put(200, "Orden 200");

        assertTrue(mapa.containsKey(100));
        assertFalse(mapa.containsKey(300));
    }

    @Test
    void debeRetornarClaveMinima() {
        TreeMap<Integer, String> mapa = new TreeMap<>();

        mapa.put(50, "Orden 50");
        mapa.put(20, "Orden 20");
        mapa.put(70, "Orden 70");

        assertEquals(20, mapa.firstKey());
    }

    @Test
    void debeEliminarClave() {
        TreeMap<Integer, String> mapa = new TreeMap<>();

        mapa.put(100, "Orden 100");

        assertEquals("Orden 100", mapa.remove(100));
        assertFalse(mapa.containsKey(100));
        assertEquals(0, mapa.size());
    }

    @Test
    void debeRetornarNullParaClaveInexistente() {
        TreeMap<Integer, String> mapa = new TreeMap<>();

        assertNull(mapa.get(100));
        assertFalse(mapa.containsKey(100));
    }

    @Test
    void debeEliminarNodoConDosHijos() {
        TreeMap<Integer, String> mapa = new TreeMap<>();

        mapa.put(50, "Orden 50");
        mapa.put(30, "Orden 30");
        mapa.put(70, "Orden 70");
        mapa.put(20, "Orden 20");
        mapa.put(40, "Orden 40");

        assertEquals("Orden 30", mapa.remove(30));

        assertFalse(mapa.containsKey(30));
        assertEquals("Orden 20", mapa.get(20));
        assertEquals("Orden 40", mapa.get(40));
        assertEquals(4, mapa.size());
    }

    @Test
    void debeEliminarRaiz() {
        TreeMap<Integer, String> mapa = new TreeMap<>();

        mapa.put(50, "Orden 50");
        mapa.put(30, "Orden 30");
        mapa.put(70, "Orden 70");

        assertEquals("Orden 50", mapa.remove(50));

        assertFalse(mapa.containsKey(50));
        assertTrue(mapa.containsKey(30));
        assertTrue(mapa.containsKey(70));
    }

    @Test
    void noDebeAceptarClaveNula() {
        TreeMap<Integer, String> mapa = new TreeMap<>();

        assertThrows(IllegalArgumentException.class,
                () -> mapa.put(null, "Orden"));
    }

    @Test
    void debeLanzarExcepcionAlConsultarMapaVacio() {
        TreeMap<Integer, String> mapa = new TreeMap<>();

        assertThrows(IllegalStateException.class,
                mapa::firstKey);
    }

    @Test
    void debeRetornarNullAlEliminarClaveInexistente() {
        TreeMap<Integer, String> mapa = new TreeMap<>();

        assertNull(mapa.remove(100));
    }
}
