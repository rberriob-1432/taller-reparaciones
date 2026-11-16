package test.estructuras;
import main.estructuras.HashMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class HashMapTest {

    @Test
    void debeAlmacenarClaveYValor() {
        HashMap<String, String> mapa = new HashMap<>();

        mapa.put("ABC123", "Toyota Corolla");

        assertEquals("Toyota Corolla", mapa.get("ABC123"));
        assertEquals(1, mapa.size());
    }

    @Test
    void debeActualizarValorDeClaveExistente() {
        HashMap<String, String> mapa = new HashMap<>();

        mapa.put("ABC123", "Toyota");
        String anterior = mapa.put("ABC123", "Mazda");

        assertEquals("Toyota", anterior);
        assertEquals("Mazda", mapa.get("ABC123"));
        assertEquals(1, mapa.size());
    }

    @Test
    void debeBuscarClaveExistente() {
        HashMap<String, String> mapa = new HashMap<>();

        mapa.put("ABC123", "Toyota");

        assertTrue(mapa.containsKey("ABC123"));
        assertFalse(mapa.containsKey("XYZ999"));
    }

    @Test
    void debeEliminarClave() {
        HashMap<String, String> mapa = new HashMap<>();

        mapa.put("ABC123", "Toyota");

        assertEquals("Toyota", mapa.remove("ABC123"));
        assertFalse(mapa.containsKey("ABC123"));
        assertEquals(0, mapa.size());
    }

    @Test
    void debeRetornarNullAlConsultarClaveInexistente() {
        HashMap<String, String> mapa = new HashMap<>();

        assertNull(mapa.get("ABC123"));
    }

    @Test
    void debeRetornarNullAlEliminarClaveInexistente() {
        HashMap<String, String> mapa = new HashMap<>();

        assertNull(mapa.remove("ABC123"));
    }

    @Test
    void noDebeAceptarClavesNulas() {
        HashMap<String, String> mapa = new HashMap<>();

        assertThrows(IllegalArgumentException.class,
                () -> mapa.put(null, "Toyota"));
    }

    @Test
    void debeResolverColisiones() {
        HashMap<Integer, String> mapa = new HashMap<>();

        mapa.put(1, "Toyota");
        mapa.put(17, "Mazda");

        assertEquals("Toyota", mapa.get(1));
        assertEquals("Mazda", mapa.get(17));
        assertEquals(2, mapa.size());
    }

    @Test
    void debeRedimensionarTablaHash() {
        HashMap<Integer, String> mapa = new HashMap<>();

        for (int i = 0; i < 100; i++) {
            mapa.put(i, "Vehiculo " + i);
        }

        assertEquals(100, mapa.size());

        for (int i = 0; i < 100; i++) {
            assertEquals("Vehiculo " + i, mapa.get(i));
        }
    }

    @Test
    void debeEliminarElementoEnCadenaDeColisiones() {
        HashMap<Integer, String> mapa = new HashMap<>();

        mapa.put(1, "Toyota");
        mapa.put(17, "Mazda");
        mapa.put(33, "Chevrolet");

        assertEquals("Mazda", mapa.remove(17));

        assertEquals("Toyota", mapa.get(1));
        assertEquals("Chevrolet", mapa.get(33));
        assertFalse(mapa.containsKey(17));
    }

    @Test
    void debeDistinguirClaveExistenteConValorNulo() {
        HashMap<String, String> mapa = new HashMap<>();

        mapa.put("ABC123", null);

        assertTrue(mapa.containsKey("ABC123"));
        assertNull(mapa.get("ABC123"));
    }
}
