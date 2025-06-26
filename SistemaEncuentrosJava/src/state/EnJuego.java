package state;

import entities.Encuentro;

public class EnJuego implements EstadoPartido {
    private Encuentro encuentro;
    public EnJuego(Encuentro e) { this.encuentro = e; }
    @Override public void manejarCambioEstado() {}
    @Override public boolean puedeUnirse() { return false; }
    @Override public boolean puedeConfirmar() { return false; }
}
