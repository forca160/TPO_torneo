package state;

import java.time.LocalDateTime;

import entities.Encuentro;

public class NecesitamosJugadores implements EstadoPartido {
    private Encuentro encuentro;

    public String getMensage(String usuario, String deporte, LocalDateTime dia) {
        return String.format("¡Hola %s! se publico un encuentro de %s el %s.",
                usuario, deporte, dia);
    }

    public NecesitamosJugadores(Encuentro e) {
        this.encuentro = e;
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
