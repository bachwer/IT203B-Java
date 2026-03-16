package Session07.Ex1;

public class EmailService {
    public void sendEmail(Customer customer, String message){
        System.out.println("Đã gửi email đến " + customer.name +": " + message);
    }
}
