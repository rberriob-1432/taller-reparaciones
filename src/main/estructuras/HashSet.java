package main.estructuras;
public class HashSet<T> {

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
        }
    }

    private Nodo<T>[] tabla;
    private int size;
    private static final int CAPACIDAD_INICIAL = 16;

    @SuppressWarnings("unchecked")
    public HashSet() {
        tabla = (Nodo<T>[]) new Nodo[CAPACIDAD_INICIAL];
    }

    public boolean add(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("No se permiten nulos");
        }

        if (contains(elemento)) {
            return false;
        }

        if ((size + 1.0) / tabla.length > 0.75) {
            resize();
        }

        int indice = indice(elemento, tabla.length);

        Nodo<T> nuevo = new Nodo<>(elemento);
        nuevo.siguiente = tabla[indice];
        tabla[indice] = nuevo;

        size++;
        return true;
    }

    public boolean contains(T elemento) {
        if (elemento == null) {
            return false;
        }

        int indice = indice(elemento, tabla.length);
        Nodo<T> actual = tabla[indice];

        while (actual != null) {
            if (actual.dato.equals(elemento)) {
                return true;
            }

            actual = actual.siguiente;
        }

        return false;
    }

    public boolean remove(T elemento) {
        if (elemento == null) {
            return false;
        }

        int indice = indice(elemento, tabla.length);

        Nodo<T> actual = tabla[indice];
        Nodo<T> anterior = null;

        while (actual != null) {
            if (actual.dato.equals(elemento)) {
                if (anterior == null) {
                    tabla[indice] = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }

                size--;
                return true;
            }

            anterior = actual;
            actual = actual.siguiente;
        }

        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int indice(T elemento, int capacidad) {
        return (elemento.hashCode() & 0x7fffffff) % capacidad;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Nodo<T>[] anterior = tabla;
        tabla = (Nodo<T>[]) new Nodo[anterior.length * 2];

        for (Nodo<T> cabeza : anterior) {
            Nodo<T> actual = cabeza;

            while (actual != null) {
                Nodo<T> siguiente = actual.siguiente;
                int nuevoIndice = indice(actual.dato, tabla.length);

                actual.siguiente = tabla[nuevoIndice];
                tabla[nuevoIndice] = actual;

                actual = siguiente;
            }
        }
    }
}
