package main.estructuras;
public class Queue<T> {

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
        }
    }

    private Nodo<T> frente;
    private Nodo<T> finalCola;
    private int size;

    public void offer(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("No se permiten nulos");
        }

        Nodo<T> nuevo = new Nodo<>(elemento);

        if (finalCola == null) {
            frente = nuevo;
            finalCola = nuevo;
        } else {
            finalCola.siguiente = nuevo;
            finalCola = nuevo;
        }

        size++;
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }

        T eliminado = frente.dato;

        frente = frente.siguiente;

        if (frente == null) {
            finalCola = null;
        }

        size--;

        return eliminado;
    }

    public T peek() {
        return isEmpty() ? null : frente.dato;
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
}
