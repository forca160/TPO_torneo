package state;

import entities.Encuentro;

public class PartidoArmado implements EstadoPartido {
    private Encuentro encuentro;
    public PartidoArmado(Encuentro e) { this.encuentro = e; }
    @Override public void manejarCambioEstado() {}
    @Override public boolean puedeUnirse() { return false; }
    @Override public boolean puedeConfirmar() { return true; }
}
