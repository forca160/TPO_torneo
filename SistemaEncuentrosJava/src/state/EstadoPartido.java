package state;

import java.time.LocalDateTime;

public interface EstadoPartido {
    void manejarCambioEstado();

    boolean puedeUnirse();

    boolean puedeConfirmar();

    String getMensage(String usuario, String deporte, LocalDateTime dia);
}
