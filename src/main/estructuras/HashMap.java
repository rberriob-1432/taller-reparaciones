package main.estructuras;
public class HashMap<K, V> {

    private static class Nodo<K, V> {
        K clave;
        V valor;
        Nodo<K, V> siguiente;

        Nodo(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }

    private Nodo<K, V>[] tabla;
    private int size;

    @SuppressWarnings("unchecked")
    public HashMap() {
        tabla = (Nodo<K, V>[]) new Nodo[16];
    }

    public V put(K clave, V valor) {
        if (clave == null) {
            throw new IllegalArgumentException("Clave nula");
        }

        int indice = indice(clave);

        Nodo<K, V> actual = tabla[indice];

        while (actual != null) {
            if (actual.clave.equals(clave)) {
                V anterior = actual.valor;
                actual.valor = valor;
                return anterior;
            }

            actual = actual.siguiente;
        }

        if ((size + 1.0) / tabla.length > 0.75) {
            resize();
            indice = indice(clave);
        }

        Nodo<K, V> nuevo = new Nodo<>(clave, valor);
        nuevo.siguiente = tabla[indice];
        tabla[indice] = nuevo;

        size++;

        return null;
    }

    public V get(K clave) {
        if (clave == null) {
            return null;
        }

        Nodo<K, V> actual = tabla[indice(clave)];

        while (actual != null) {
            if (actual.clave.equals(clave)) {
                return actual.valor;
            }

            actual = actual.siguiente;
        }

        return null;
    }

    public boolean containsKey(K clave) {
        if (clave == null) {
            return false;
        }

        Nodo<K, V> actual = tabla[indice(clave)];

        while (actual != null) {
            if (actual.clave.equals(clave)) {
                return true;
            }

            actual = actual.siguiente;
        }

        return false;
    }

    public V remove(K clave) {
        if (clave == null) {
            return null;
        }

        int indice = indice(clave);

        Nodo<K, V> actual = tabla[indice];
        Nodo<K, V> anterior = null;

        while (actual != null) {
            if (actual.clave.equals(clave)) {
                if (anterior == null) {
                    tabla[indice] = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }

                size--;
                return actual.valor;
            }

            anterior = actual;
            actual = actual.siguiente;
        }

        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int indice(K clave) {
        return (clave.hashCode() & 0x7fffffff) % tabla.length;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Nodo<K, V>[] anterior = tabla;
        tabla = (Nodo<K, V>[]) new Nodo[anterior.length * 2];

        for (Nodo<K, V> cabeza : anterior) {
            Nodo<K, V> actual = cabeza;

            while (actual != null) {
                Nodo<K, V> siguiente = actual.siguiente;
                int nuevoIndice = indice(actual.clave);

                actual.siguiente = tabla[nuevoIndice];
                tabla[nuevoIndice] = actual;

                actual = siguiente;
            }
        }
    }
}
