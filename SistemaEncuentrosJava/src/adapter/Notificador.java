package adapter;

import observer.Notificacion;

public class Notificador {
    private ServicioNotificacion servicio;

    public void setServicio(ServicioNotificacion servicio) {
        this.servicio = servicio;
    }

    public void enviar(Notificacion notificacion) {
        if (servicio != null) {
            servicio.enviarNotificacion(notificacion);
        }
    }
}
