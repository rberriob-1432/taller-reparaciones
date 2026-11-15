package main.repository;

import main.estructuras.HashMap;
import main.model.Cliente;

public class ClienteRepository {

    private final HashMap<String, Cliente> clientes;

    public ClienteRepository() {
        this.clientes = new HashMap<>();
    }

    public void registrar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser null");
        }

        String identificacion = cliente.getIdentificacion();

        if (clientes.containsKey(identificacion)) {
            throw new IllegalArgumentException(
                    "Ya existe un cliente con esa identificación"
            );
        }

        clientes.put(identificacion, cliente);
    }

    public Cliente buscarPorIdentificacion(String identificacion) {
        if (identificacion == null) {
            return null;
        }

        return clientes.get(identificacion.trim());
    }

    public boolean existe(String identificacion) {
        if (identificacion == null) {
            return false;
        }

        return clientes.containsKey(identificacion.trim());
    }

    public boolean actualizar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser null");
        }

        String identificacion = cliente.getIdentificacion();

        if (!clientes.containsKey(identificacion)) {
            return false;
        }

        clientes.put(identificacion, cliente);
        return true;
    }

    public boolean eliminar(String identificacion) {
        if (identificacion == null) {
            return false;
        }

        String clave = identificacion.trim();

        if (!clientes.containsKey(clave)) {
            return false;
        }

        clientes.remove(clave);
        return true;
    }

    public int cantidad() {
        return clientes.size();
    }

    public boolean estaVacio() {
        return clientes.isEmpty();
    }
}
