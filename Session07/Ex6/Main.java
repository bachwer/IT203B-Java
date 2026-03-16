package Session07.Ex6;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Chọn kênh bán");
        System.out.println("1. Website");
        System.out.println("2. Mobile App");
        System.out.println("3. POS");

        int choice = sc.nextInt();

        SalesChannelFactory factory = null;

        if(choice == 1) {

            System.out.println("Bạn đã chọn kênh Website");

            factory = new WebsiteFactory();
        }
        else if(choice == 2) {

            System.out.println("Bạn đã chọn kênh Mobile");

            factory = new MobileFactory();
        }
        else if(choice == 3) {

            System.out.println("Bạn đã chọn POS");

            factory = new POSFactory();
        }

        OrderService service = new OrderService(factory);

        service.processOrder(15000000);
    }
}