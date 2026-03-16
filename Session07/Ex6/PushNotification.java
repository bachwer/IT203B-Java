package Session07.Ex6;
class PushNotification implements NotificationService {

    public void send(String message) {

        System.out.println("Gửi push notification: " + message);
    }
}