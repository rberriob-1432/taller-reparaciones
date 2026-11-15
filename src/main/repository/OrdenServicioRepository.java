package main.repository;

import main.estructuras.ArrayList;
import main.estructuras.PriorityQueue;
import main.estructuras.TreeMap;
import main.model.EstadoOrden;
import main.model.OrdenServicio;

public class OrdenServicioRepository {

    private final TreeMap<String, OrdenServicio> ordenesPorCodigo;
    private final ArrayList<OrdenServicio> ordenes;

    public OrdenServicioRepository() {
        ordenesPorCodigo = new TreeMap<>();
        ordenes = new ArrayList<>();
    }

    public void registrar(OrdenServicio orden) {
        if (orden == null) {
            throw new IllegalArgumentException("La orden no puede ser null");
        }

        String codigo = orden.getCodigo();

        if (ordenesPorCodigo.containsKey(codigo)) {
            throw new IllegalArgumentException("Código de orden duplicado");
        }

        ordenesPorCodigo.put(codigo, orden);
        ordenes.add(orden);
    }

    public OrdenServicio buscarPorCodigo(String codigo) {
        if (codigo == null) {
            return null;
        }
        return ordenesPorCodigo.get(codigo.trim());
    }

    public boolean existe(String codigo) {
        return codigo != null && ordenesPorCodigo.containsKey(codigo.trim());
    }

    public boolean actualizar(OrdenServicio orden) {
        if (orden == null) {
            throw new IllegalArgumentException("La orden no puede ser null");
        }

        String codigo = orden.getCodigo();

        if (!ordenesPorCodigo.containsKey(codigo)) {
            return false;
        }

        ordenesPorCodigo.put(codigo, orden);

        for (int i = 0; i < ordenes.size(); i++) {
            if (ordenes.get(i).getCodigo().equals(codigo)) {
                ordenes.set(i, orden);
                return true;
            }
        }

        return false;
    }

    public boolean eliminar(String codigo) {
        if (codigo == null) {
            return false;
        }

        String clave = codigo.trim();

        if (!ordenesPorCodigo.containsKey(clave)) {
            return false;
        }

        ordenesPorCodigo.remove(clave);

        for (int i = 0; i < ordenes.size(); i++) {
            if (ordenes.get(i).getCodigo().equals(clave)) {
                ordenes.remove(i);
                break;
            }
        }

        return true;
    }

    public ArrayList<OrdenServicio> listarPorPrioridad() {
        PriorityQueue<OrdenServicio> cola = new PriorityQueue<>();
        ArrayList<OrdenServicio> resultado = new ArrayList<>();

        for (int i = 0; i < ordenes.size(); i++) {
            OrdenServicio orden = ordenes.get(i);

            if (orden.getEstado() != EstadoOrden.FINALIZADA
                    && orden.getEstado() != EstadoOrden.CANCELADA) {
                cola.offer(orden);
            }
        }

        while (!cola.isEmpty()) {
            resultado.add(cola.poll());
        }

        return resultado;
    }

    public int cantidad() {
        return ordenes.size();
    }

    public boolean estaVacio() {
        return ordenes.isEmpty();
    }
}
