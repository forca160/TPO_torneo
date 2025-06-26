package entities;

import java.time.LocalDateTime;
import java.util.Map;

public class EstadisticasPartido {
    private String comentarios;
    private Map<Usuario, String> comentariosJugadores;
    private LocalDateTime fechaFinalizacion;

    public void registrarComentario() {
        // Add comment logic
    }

    public String obtenerResumen() {
        // Return summary
        return comentarios;
    }
}
