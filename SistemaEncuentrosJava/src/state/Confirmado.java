package state;

import entities.Encuentro;

public class Confirmado implements EstadoPartido {
    private Encuentro encuentro;

    public Confirmado(Encuentro encuentro) {
        this.encuentro = encuentro;
    }

    @Override
    public void manejarCambioEstado() {
        System.out.println("Estado cambiado: Confirmado");
        if (LocalDateTime.now().isAfter(encuentro.getHorario())) {
            encuentro.cambiarEstado(new EnJuego(encuentro));
            encuentro.notificar(TipoNotificacion.PARTIDO_EN_JUEGO);
        }
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
