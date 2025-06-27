package state;

import java.time.LocalDateTime;

import entities.Encuentro;
import entities.Deporte;

public interface EstadoPartido {
    void manejarCambioEstado();

    boolean puedeUnirse();

    boolean puedeConfirmar();

    String getMensage(String usuario, String deporte, LocalDateTime dia);
}
 