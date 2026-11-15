package main.model;
public class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int anio;
    private String color;
    public Vehiculo(String placa, String marca,
                    String modelo, int anio, String color) {

        if (placa == null || placa.isBlank()) {
            throw new IllegalArgumentException("La placa es obligatoria");
        }

        if (marca == null || marca.isBlank()) {
            throw new IllegalArgumentException("La marca es obligatoria");
        }
        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("El modelo es obligatorio");
        }
        if (anio <= 0) {
            throw new IllegalArgumentException("Año inválido");
        }
        this.placa = placa.trim().toUpperCase();
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.color = color;
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
    public int getAnio() {
        return anio;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anio=" + anio +
                ", color='" + color + '\'' +
                '}';
    }
}