package adapter;

public class FirebaseService {
    public void sendPushNotification(String userToken, String message) {
        System.out.println("Notificacion para: " + userToken + "de FireBase");
        System.out.println(message);
    }
}
