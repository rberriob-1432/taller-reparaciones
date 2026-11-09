package main.java.domain.enums;
public enum Prioridad {

    BAJA(1),
    MEDIA(2),
    ALTA(3),
    CRITICA(4);

    private final int nivel;

    Prioridad(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }
}