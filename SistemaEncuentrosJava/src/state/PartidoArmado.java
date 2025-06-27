package state;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import entities.Encuentro;

public class PartidoArmado implements EstadoPartido {
    private Encuentro encuentro;

    public PartidoArmado(Encuentro e) {
        this.encuentro = e;
    }

    public String getMensage(String usuario, String deporte, LocalDateTime dia) {
        return String.format(
                "¡Hola %s! El encuentro de %s del %s ya está completo y nececita que confirmes tu asistencia.",
                usuario, deporte, dia);
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
        return true;
    }
}
