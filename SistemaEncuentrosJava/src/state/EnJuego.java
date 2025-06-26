package state;

import entities.Encuentro;

package state;

import entities.Encuentro;

public class EnJuego implements EstadoPartido {
    private Encuentro encuentro;

    public EnJuego(Encuentro encuentro) {
        this.encuentro = encuentro;
    }

    @Override
    public void manejarCambioEstado() {
        System.out.println("Estado actual: En Juego");
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
