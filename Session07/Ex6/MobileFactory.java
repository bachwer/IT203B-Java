package Session07.Ex6;
class MobileFactory implements SalesChannelFactory {

    public DiscountStrategy createDiscount() {
        return new MobileFirstOrderDiscount();
    }

    public PaymentMethod createPayment() {
        return new MomoPayment();
    }

    public NotificationService createNotification() {
        return new PushNotification();
    }
}