package state;

import entities.Encuentro;

public class Finalizado implements EstadoPartido {
    private Encuentro encuentro;
    private static String mensage = "¡Hola %s! El encuentro de %s del %s ya Finalizo.";

    public Finalizado(Encuentro e) {
        this.encuentro = e;
    }

    public String getMensage() {
        return mensage;
    }

    @Override
    public void manejarCambioEstado() {
    }

    @Override
    public boolean puedeUnirse() {
        return false;
    }

    @Override
    public boolean puedeConfirmar() {
        return false;
    }
}
