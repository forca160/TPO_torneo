package state;

import entities.Encuentro;

public class NecesitamosJugadores implements EstadoPartido {
    private Encuentro encuentro;
    private static String mensage = "¡Hola %s! se publico un encuentro de %s el %s.";

    public NecesitamosJugadores(Encuentro e) {
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
        return true;
    }

    @Override
    public boolean puedeConfirmar() {
        return false;
    }
}
