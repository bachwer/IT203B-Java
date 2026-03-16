package Session07.Ex4;

public class OrderService {
    OrderRepository repository;
    NotificationService notificationService;

    public OrderService(OrderRepository repository,
                        NotificationService notificationService) {

        this.repository = repository;
        this.notificationService = notificationService;
    }

    public void createOrder(String orderId){
        Order order = new Order(orderId);

        repository.save(order);
        notificationService.send(
                "Đơn hàng " + orderId + " đã được tạo",
                "customer"
        );    }
}
