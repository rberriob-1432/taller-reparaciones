package main.estructuras;
public class Deque<T> {

    private static class Nodo<T> {
        T dato;
        Nodo<T> anterior;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
        }
    }

    private Nodo<T> frente;
    private Nodo<T> finalCola;
    private int size;

    public void addFirst(T elemento) {
        validar(elemento);

        Nodo<T> nuevo = new Nodo<>(elemento);

        nuevo.siguiente = frente;

        if (frente != null) {
            frente.anterior = nuevo;
        } else {
            finalCola = nuevo;
        }

        frente = nuevo;
        size++;
    }

    public void addLast(T elemento) {
        validar(elemento);

        Nodo<T> nuevo = new Nodo<>(elemento);

        nuevo.anterior = finalCola;

        if (finalCola != null) {
            finalCola.siguiente = nuevo;
        } else {
            frente = nuevo;
        }

        finalCola = nuevo;
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T eliminado = frente.dato;

        frente = frente.siguiente;

        if (frente != null) {
            frente.anterior = null;
        } else {
            finalCola = null;
        }

        size--;
        return eliminado;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T eliminado = finalCola.dato;

        finalCola = finalCola.anterior;

        if (finalCola != null) {
            finalCola.siguiente = null;
        } else {
            frente = null;
        }

        size--;
        return eliminado;
    }

    public T peekFirst() {
        return isEmpty() ? null : frente.dato;
    }

    public T peekLast() {
        return isEmpty() ? null : finalCola.dato;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        frente = null;
        finalCola = null;
        size = 0;
    }

    private void validar(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("No se permiten nulos");
        }
    }
}
