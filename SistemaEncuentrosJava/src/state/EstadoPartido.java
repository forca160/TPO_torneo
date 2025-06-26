package state;

import entities.Encuentro;

public interface EstadoPartido {
    void manejarCambioEstado();

    boolean puedeUnirse();

    boolean puedeConfirmar();

    String getMensage();
}
