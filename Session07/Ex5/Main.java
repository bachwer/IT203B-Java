package Session07.Ex5;
import java.util.*;

public class Main {

    static List<Product> products = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        OrderRepository repository = new FileOrderRepository();
        NotificationService notification = new EmailNotification();

        OrderService service = new OrderService(repository, notification);

        while(true) {

            System.out.println("\n===== MENU =====");

            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Thêm khách hàng");
            System.out.println("3. Tạo đơn hàng");
            System.out.println("4. Xem đơn hàng");
            System.out.println("5. Tính doanh thu");
            System.out.println("0. Thoát");

            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1) {

                System.out.print("Mã: ");
                String id = sc.nextLine();

                System.out.print("Tên: ");
                String name = sc.nextLine();

                System.out.print("Giá: ");
                double price = sc.nextDouble();
                sc.nextLine();

                System.out.print("Danh mục: ");
                String category = sc.nextLine();

                products.add(new Product(id, name, price, category));

                System.out.println("Đã thêm sản phẩm " + id);
            }

            else if(choice == 2) {

                System.out.print("Tên: ");
                String name = sc.nextLine();

                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Phone: ");
                String phone = sc.nextLine();

                customers.add(new Customer(name, email, phone));

                System.out.println("Đã thêm khách hàng");
            }

            else if(choice == 3) {

                Customer c = customers.get(0);
                Product p = products.get(0);

                Order order = new Order("ORD001", c);

                order.addItem(new OrderItem(p, 1));

                DiscountStrategy discount = new PercentageDiscount(10);

                PaymentMethod payment = new CreditCardPayment();

                service.createOrder(order, discount, payment);
            }

            else if(choice == 4) {

                for(Order o : repository.findAll()) {

                    System.out.println(o.orderId +
                            " - " +
                            o.customer.name);
                }
            }

            else if(choice == 5) {

                double revenue = 0;

                for(Order o : repository.findAll()) {

                    revenue += o.CalculateTotal();
                }

                System.out.println("Tổng doanh thu: " + revenue);
            }

            else if(choice == 0) {

                break;
            }
        }
    }
}