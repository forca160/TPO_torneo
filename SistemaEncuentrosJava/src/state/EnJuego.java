package state;

import java.time.LocalDateTime;

import entities.Encuentro;

public class EnJuego implements EstadoPartido {
    private Encuentro encuentro;

    public EnJuego(Encuentro encuentro) {
        this.encuentro = encuentro;
    }

    @Override
    public void manejarCambioEstado() {
        this.encuentro.notificar();
    }

    @Override
    public boolean puedeUnirse() {
        return false;
    }

    @Override
    public boolean puedeConfirmar() {
        return false;
    }

    public String getMensage(String usuario, String deporte, LocalDateTime dia) {
        return String.format("¡Hola %s! El encuentro de %s del %s esta en Juego",
                usuario, deporte, dia);
    }

}
