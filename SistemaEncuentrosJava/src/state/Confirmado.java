package state;

import entities.Encuentro;

public class Confirmado implements EstadoPartido {
    private Encuentro encuentro;
    private static String mensage = "¡Hola %s! El encuentro de %s del %s ya fue confirmado por todos los.";

    public Confirmado(Encuentro e) {
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
