package observer;

import entities.Usuario;
import java.time.LocalDateTime;

public class Notificacion {
    private Usuario destinatario;
    private String mensaje;
    private TipoNotificacion tipo;
    private LocalDateTime fechaCreacion;

    public Notificacion(Usuario destinatario, String mensaje, TipoNotificacion tipo) {
        this.destinatario = destinatario;
        this.mensaje = mensaje;
        this.tipo = tipo;
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
