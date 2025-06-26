package adapter;

import observer.Notificacion;

public class AdapterJavaMail implements ServicioNotificacion {
    private JavaMailService javaMailService;

    public AdapterJavaMail(JavaMailService svc) { this.javaMailService = svc; }
    @Override
    public void enviarNotificacion(Notificacion notificacion) {
        javaMailService.sendEmail(
            notificacion.getDestinatario().getEmail(), "Notificación", notificacion.getMensaje()
        );
    }
}
