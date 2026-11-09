package main.java.domain.model;
import java.util.Objects;

public class Vehiculo {

    private final String placa;
    private final String marca;
    private final String modelo;
    private final Cliente propietario;

    public Vehiculo(String placa, String marca, String modelo, Cliente propietario) {

        if (placa == null || placa.isBlank()) {
            throw new IllegalArgumentException("La placa es obligatoria");
        }

        if (marca == null || marca.isBlank()) {
            throw new IllegalArgumentException("La marca es obligatoria");
        }

        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("El modelo es obligatorio");
        }

        if (propietario == null) {
            throw new IllegalArgumentException("El vehículo debe tener un propietario");
        }

        this.placa = placa.trim().toUpperCase();
        this.marca = marca.trim();
        this.modelo = modelo.trim();
        this.propietario = propietario;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Cliente getPropietario() {
        return propietario;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Vehiculo vehiculo)) return false;

        return placa.equals(vehiculo.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa);
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", propietario=" + propietario.getNombre() +
                '}';
    }
}