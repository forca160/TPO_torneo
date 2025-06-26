package adapter;

import observer.Notificacion;

public class AdapterFirebase implements ServicioNotificacion {
    private FirebaseService firebaseService;

    public AdapterFirebase(FirebaseService svc) {
        this.firebaseService = svc;
    }

    @Override
    public void enviarNotificacion(Notificacion notificacion) {
        firebaseService.sendPushNotification(
                notificacion.getUsuario(), notificacion.getMensaje());
    }
}
