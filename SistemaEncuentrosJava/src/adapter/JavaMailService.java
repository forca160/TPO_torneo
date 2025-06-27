package adapter;

public class JavaMailService {
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Notificacion para: " + to + " de: " + subject + " de JavaMail");
        System.out.println(body);
    }
}
