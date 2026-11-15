package main.model;
public enum Prioridad {

    CRITICA(1),
    ALTA(2),
    MEDIA(3),
    BAJA(4);

    private final int nivel;

    Prioridad(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }
}