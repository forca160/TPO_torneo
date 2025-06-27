package adapter;

import observer.Notificacion;

public class AdapterJavaMail implements ServicioNotificacion {
    private JavaMailService javaMailService;

    public AdapterJavaMail(JavaMailService svc) {
        this.javaMailService = svc;
    }

    @Override
    public void enviarNotificacion(Notificacion notificacion) {
        javaMailService.sendEmail(
                notificacion.getEmail(), "eventos-noresponse@gmail.com", notificacion.getMensaje());
    }
}
