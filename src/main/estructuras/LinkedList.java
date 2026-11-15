package main.estructuras;
public class LinkedList<T> {

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
        }
    }

    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int size;

    public void add(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);

        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
        }

        size++;
    }

    public void addFirst(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);

        nuevo.siguiente = cabeza;
        cabeza = nuevo;

        if (cola == null) {
            cola = nuevo;
        }

        size++;
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Lista vacía");
        }

        return cabeza.dato;
    }

    public T getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Lista vacía");
        }

        return cola.dato;
    }

    public boolean contains(T elemento) {
        Nodo<T> actual = cabeza;

        while (actual != null) {
            if (elemento == null
                    ? actual.dato == null
                    : elemento.equals(actual.dato)) {
                return true;
            }

            actual = actual.siguiente;
        }

        return false;
    }

    public boolean remove(T elemento) {
        Nodo<T> actual = cabeza;
        Nodo<T> anterior = null;

        while (actual != null) {
            boolean coincide = elemento == null
                    ? actual.dato == null
                    : elemento.equals(actual.dato);

            if (coincide) {
                if (anterior == null) {
                    cabeza = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }

                if (actual == cola) {
                    cola = anterior;
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

    public void clear() {
        cabeza = null;
        cola = null;
        size = 0;
    }
}
