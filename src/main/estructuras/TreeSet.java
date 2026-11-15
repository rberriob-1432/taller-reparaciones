package main.estructuras;
public class TreeSet<T extends Comparable<T>> {

    private class Nodo {
        T dato;
        Nodo izquierdo;
        Nodo derecho;

        Nodo(T dato) {
            this.dato = dato;
        }
    }

    private Nodo raiz;
    private int size;

    public boolean add(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("No se permiten nulos");
        }

        if (contains(elemento)) {
            return false;
        }

        raiz = insertar(raiz, elemento);
        size++;

        return true;
    }

    private Nodo insertar(Nodo actual, T elemento) {
        if (actual == null) {
            return new Nodo(elemento);
        }

        int comparacion = elemento.compareTo(actual.dato);

        if (comparacion < 0) {
            actual.izquierdo = insertar(actual.izquierdo, elemento);
        } else if (comparacion > 0) {
            actual.derecho = insertar(actual.derecho, elemento);
        }

        return actual;
    }

    public boolean contains(T elemento) {
        Nodo actual = raiz;

        while (actual != null) {
            int comparacion = elemento.compareTo(actual.dato);

            if (comparacion == 0) {
                return true;
            }

            actual = comparacion < 0
                    ? actual.izquierdo
                    : actual.derecho;
        }

        return false;
    }

    public boolean remove(T elemento) {
        if (!contains(elemento)) {
            return false;
        }

        raiz = eliminar(raiz, elemento);
        size--;

        return true;
    }

    private Nodo eliminar(Nodo actual, T elemento) {
        if (actual == null) {
            return null;
        }

        int comparacion = elemento.compareTo(actual.dato);

        if (comparacion < 0) {
            actual.izquierdo = eliminar(actual.izquierdo, elemento);
        } else if (comparacion > 0) {
            actual.derecho = eliminar(actual.derecho, elemento);
        } else {
            if (actual.izquierdo == null) {
                return actual.derecho;
            }

            if (actual.derecho == null) {
                return actual.izquierdo;
            }

            Nodo sucesor = minimo(actual.derecho);
            actual.dato = sucesor.dato;
            actual.derecho = eliminar(actual.derecho, sucesor.dato);
        }

        return actual;
    }

    private Nodo minimo(Nodo actual) {
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }

        return actual;
    }

    public T first() {
        if (raiz == null) {
            throw new IllegalStateException("Árbol vacío");
        }

        return minimo(raiz).dato;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
