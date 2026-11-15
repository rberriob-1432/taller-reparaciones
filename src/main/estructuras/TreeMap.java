package main.estructuras;
public class TreeMap<K extends Comparable<K>, V> {

    private class Nodo {
        K clave;
        V valor;
        Nodo izquierdo;
        Nodo derecho;

        Nodo(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }

    private Nodo raiz;
    private int size;

    public V put(K clave, V valor) {
        if (clave == null) {
            throw new IllegalArgumentException("Clave nula");
        }

        Nodo existente = buscarNodo(clave);

        if (existente != null) {
            V anterior = existente.valor;
            existente.valor = valor;
            return anterior;
        }

        raiz = insertar(raiz, clave, valor);
        size++;

        return null;
    }

    private Nodo insertar(Nodo actual, K clave, V valor) {
        if (actual == null) {
            return new Nodo(clave, valor);
        }

        int comparacion = clave.compareTo(actual.clave);

        if (comparacion < 0) {
            actual.izquierdo = insertar(actual.izquierdo, clave, valor);
        } else if (comparacion > 0) {
            actual.derecho = insertar(actual.derecho, clave, valor);
        }

        return actual;
    }

    public V get(K clave) {
        Nodo encontrado = buscarNodo(clave);

        return encontrado == null ? null : encontrado.valor;
    }

    public boolean containsKey(K clave) {
        return buscarNodo(clave) != null;
    }

    private Nodo buscarNodo(K clave) {
        if (clave == null) {
            return null;
        }

        Nodo actual = raiz;

        while (actual != null) {
            int comparacion = clave.compareTo(actual.clave);

            if (comparacion == 0) {
                return actual;
            }

            actual = comparacion < 0
                    ? actual.izquierdo
                    : actual.derecho;
        }

        return null;
    }

    public V remove(K clave) {
        Nodo encontrado = buscarNodo(clave);

        if (encontrado == null) {
            return null;
        }

        V anterior = encontrado.valor;
        raiz = eliminar(raiz, clave);
        size--;

        return anterior;
    }

    private Nodo eliminar(Nodo actual, K clave) {
        if (actual == null) {
            return null;
        }

        int comparacion = clave.compareTo(actual.clave);

        if (comparacion < 0) {
            actual.izquierdo = eliminar(actual.izquierdo, clave);
        } else if (comparacion > 0) {
            actual.derecho = eliminar(actual.derecho, clave);
        } else {
            if (actual.izquierdo == null) {
                return actual.derecho;
            }

            if (actual.derecho == null) {
                return actual.izquierdo;
            }

            Nodo sucesor = minimo(actual.derecho);

            actual.clave = sucesor.clave;
            actual.valor = sucesor.valor;

            actual.derecho = eliminar(
                    actual.derecho,
                    sucesor.clave
            );
        }

        return actual;
    }

    private Nodo minimo(Nodo actual) {
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }

        return actual;
    }

    public K firstKey() {
        if (raiz == null) {
            throw new IllegalStateException("Mapa vacío");
        }

        return minimo(raiz).clave;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
