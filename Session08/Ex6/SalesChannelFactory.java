package Session08.Ex6;

public interface SalesChannelFactory {
    Discount createDiscount();
    Payment createPayment();
    Notify createNotify();
}
