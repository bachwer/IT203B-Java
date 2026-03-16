package Session07.Ex1;

public class Main {

    public static void main(String[] args) {

        // Tạo sản phẩm
        Product p1 = new Product("SP01", "Laptop", 15000000);
        Product p2 = new Product("SP02", "Chuột", 300000);

        System.out.println("Tạo sản phẩm: SP01 - Laptop - 15000000, SP02 - Chuột - 300000");
        System.out.println("Đã thêm sản phẩm SP01, SP02");

        // Tạo khách hàng
        Customer customer = new Customer("Nguyễn Văn A", "a@example.com", "Hà Nội");

        System.out.println("Tạo khách hàng: Nguyễn Văn A - a@example.com");
        System.out.println("Đã thêm khách hàng");

        // Tạo đơn hàng
        Order order = new Order("ORD001", customer);
        order.addProduct(p1, 1);
        order.addProduct(p2, 2);

        System.out.println("Tạo đơn hàng: SP01 (1 cái), SP02 (2 cái)");
        System.out.println("Đơn hàng ORD001 được tạo");

        // Tính tiền
        System.out.println("Tính tổng tiền");

        OrderCalculator calculator = new OrderCalculator();
        double total = calculator.calculateTotal(order);

        order.setTotal(total);

        System.out.println("Tổng tiền: " + (int) total);

        // Lưu đơn
        System.out.println("Lưu đơn hàng");

        OrderRepository repository = new OrderRepository();
        repository.save(order);

        // Gửi email
        System.out.println("Gửi email xác nhận");

        EmailService emailService = new EmailService();
        emailService.sendEmail(customer, "Đơn hàng ORD001 đã được tạo");
    }
}