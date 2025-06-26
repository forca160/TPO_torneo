package state;

import entities.Encuentro;

public class NecesitamosJugadores implements EstadoPartido {
    private Encuentro encuentro;
    public NecesitamosJugadores(Encuentro e) { this.encuentro = e; }
    @Override public void manejarCambioEstado() {}
    @Override public boolean puedeUnirse() { return true; }
    @Override public boolean puedeConfirmar() { return false; }
}
