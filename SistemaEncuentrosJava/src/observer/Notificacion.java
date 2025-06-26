package observer;

import entities.Encuentro;
import entities.Usuario;
import java.time.LocalDateTime;

public class Notificacion {
    private Usuario destinatario;
    private String mensaje;
    private Encuentro encuentro;
    private LocalDateTime fechaCreacion;

    public Notificacion(Usuario destinatario, String mensaje, Encuentro encuentro) {
        this.destinatario = destinatario;
        this.mensaje = mensaje;
        this.encuentro = encuentro;
        this.fechaCreacion = LocalDateTime.now();
    }

    public String getEmail() {
        return this.destinatario.getEmail();
    }

    public String getUsuario() {
        return this.destinatario.getUsuario();
    }

    public String getMensaje() {
        return this.mensaje;
    }
}
