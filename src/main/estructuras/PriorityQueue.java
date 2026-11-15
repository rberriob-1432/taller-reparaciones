package main.estructuras;
public class PriorityQueue<T extends Comparable<T>> {

    private Object[] elementos;
    private int size;

    private static final int CAPACIDAD_INICIAL = 10;

    public PriorityQueue() {
        elementos = new Object[CAPACIDAD_INICIAL];
        size = 0;
    }

    public void offer(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("No se permiten nulos");
        }

        if (size == elementos.length) {
            resize();
        }

        elementos[size] = elemento;

        subir(size);
        size++;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }

        return obtener(0);
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }

        T eliminado = obtener(0);

        elementos[0] = elementos[size - 1];
        elementos[size - 1] = null;
        size--;

        if (!isEmpty()) {
            bajar(0);
        }

        return eliminado;
    }

    private void subir(int indice) {
        while (indice > 0) {
            int padre = (indice - 1) / 2;

            T actual = obtener(indice);
            T elementoPadre = obtener(padre);

            if (actual.compareTo(elementoPadre) >= 0) {
                break;
            }

            intercambiar(indice, padre);
            indice = padre;
        }
    }

    private void bajar(int indice) {
        while (true) {
            int izquierdo = 2 * indice + 1;
            int derecho = 2 * indice + 2;

            int menor = indice;

            if (izquierdo < size &&
                    obtener(izquierdo).compareTo(obtener(menor)) < 0) {
                menor = izquierdo;
            }

            if (derecho < size &&
                    obtener(derecho).compareTo(obtener(menor)) < 0) {
                menor = derecho;
            }

            if (menor == indice) {
                break;
            }

            intercambiar(indice, menor);
            indice = menor;
        }
    }

    private void intercambiar(int primero, int segundo) {
        Object temporal = elementos[primero];

        elementos[primero] = elementos[segundo];
        elementos[segundo] = temporal;
    }

    @SuppressWarnings("unchecked")
    private T obtener(int indice) {
        return (T) elementos[indice];
    }

    private void resize() {
        Object[] nuevo = new Object[elementos.length * 2];

        for (int i = 0; i < size; i++) {
            nuevo[i] = elementos[i];
        }

        elementos = nuevo;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elementos[i] = null;
        }

        size = 0;
    }
}
