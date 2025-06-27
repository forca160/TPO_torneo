package state;

import java.time.LocalDateTime;

import entities.Encuentro;
import entities.Usuario;

public class Finalizado implements EstadoPartido {
    private Encuentro encuentro;

    public String getMensage(String usuario, String deporte, LocalDateTime dia) {
        return String.format("¡Hola %s! El encuentro de %s del %s ya Finalizo.",
                usuario, deporte, dia);
    }

    public Finalizado(Encuentro e) {
        this.encuentro = e;
    }

    @Override
    public void manejarCambioEstado() {
        for (Usuario u : encuentro.getParticipantes()) {
        u.agregarEncuentroAlHistorial(encuentro);
    }
        encuentro.notificar();

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
