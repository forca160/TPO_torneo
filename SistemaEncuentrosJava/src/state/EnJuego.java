package state;

import entities.Encuentro;

public class EnJuego implements EstadoPartido {
    private Encuentro encuentro;
    private static String mensage = "¡Hola %s! El encuentro de %s del %s esta en Juego";

    public EnJuego(Encuentro e) {
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
