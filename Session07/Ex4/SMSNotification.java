package Session07.Ex4;

public class SMSNotification  implements NotificationService {

    @Override
    public void send(String message, String recipient) {
        System.out.println("Gửi SMS: " + message);
    }
}
