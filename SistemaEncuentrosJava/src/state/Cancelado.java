package state;

import entities.Encuentro;

public class Cancelado implements EstadoPartido {
    private Encuentro encuentro;
    public Cancelado(Encuentro e) { this.encuentro = e; }
    @Override public void manejarCambioEstado() {}
    @Override public boolean puedeUnirse() { return false; }
    @Override public boolean puedeConfirmar() { return false; }
}
