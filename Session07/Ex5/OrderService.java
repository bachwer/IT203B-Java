package Session07.Ex5;
class OrderService {

    OrderRepository repository;
    NotificationService notification;

    public OrderService(OrderRepository repository,
                        NotificationService notification) {

        this.repository = repository;
        this.notification = notification;
    }

    public void createOrder(Order order,
                            DiscountStrategy discount,
                            PaymentMethod payment) {

        double total = order.CalculateTotal();

        double finalAmount = discount.applyDiscount(total);

        payment.pay(finalAmount);

        repository.save(order);

        notification.send(
                "Đơn hàng " + order.orderId + " đã được tạo",
                order.customer.email
        );
    }
}