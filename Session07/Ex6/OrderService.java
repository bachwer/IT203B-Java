package Session07.Ex6;
class OrderService {

    private DiscountStrategy discount;
    private PaymentMethod payment;
    private NotificationService notification;

    public OrderService(SalesChannelFactory factory) {

        this.discount = factory.createDiscount();
        this.payment = factory.createPayment();
        this.notification = factory.createNotification();
    }

    public void processOrder(double total) {

        double finalAmount = discount.applyDiscount(total);

        payment.pay(finalAmount);

        notification.send("Đơn hàng thành công");
    }
}