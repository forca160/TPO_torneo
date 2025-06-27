package state;

import entities.Encuentro;
import observer.TipoNotificacion;

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
        this.encuentro.notificar(TipoNotificacion.NUEVO_PARTIDO_DEPORTE_FAVORITO);
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
