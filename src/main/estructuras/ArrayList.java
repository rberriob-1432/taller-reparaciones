package main.estructuras;
public class ArrayList<T> {

    private Object[] elementos;
    private int cantidad;

    private static final int CAPACIDAD_INICIAL = 10;

    public ArrayList() {
        elementos = new Object[CAPACIDAD_INICIAL];
        cantidad = 0;
    }

    public void add(T elemento) {
        asegurarCapacidad();
        elementos[cantidad] = elemento;
        cantidad++;
    }

    @SuppressWarnings("unchecked")
    public T get(int indice) {
        validarIndice(indice);
        return (T) elementos[indice];
    }

    public void set(int indice, T elemento) {
        validarIndice(indice);
        elementos[indice] = elemento;
    }

    public T remove(int indice) {
        validarIndice(indice);

        T eliminado = get(indice);

        for (int i = indice; i < cantidad - 1; i++) {
            elementos[i] = elementos[i + 1];
        }

        elementos[cantidad - 1] = null;
        cantidad--;

        return eliminado;
    }

    public boolean contains(T elemento) {
        for (int i = 0; i < cantidad; i++) {
            if (elemento == null) {
                if (elementos[i] == null) {
                    return true;
                }
            } else if (elemento.equals(elementos[i])) {
                return true;
            }
        }

        return false;
    }

    public int size() {
        return cantidad;
    }

    public boolean isEmpty() {
        return cantidad == 0;
    }

    public void clear() {
        for (int i = 0; i < cantidad; i++) {
            elementos[i] = null;
        }

        cantidad = 0;
    }

    private void asegurarCapacidad() {
        if (cantidad == elementos.length) {
            Object[] nuevoArreglo = new Object[elementos.length * 2];

            for (int i = 0; i < cantidad; i++) {
                nuevoArreglo[i] = elementos[i];
            }

            elementos = nuevoArreglo;
        }
    }

    private void validarIndice(int indice) {
        if (indice < 0 || indice >= cantidad) {
            throw new IndexOutOfBoundsException(
                    "Índice fuera de rango: " + indice
            );
        }
    }
}
