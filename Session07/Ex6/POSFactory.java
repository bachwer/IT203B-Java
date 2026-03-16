package Session07.Ex6;
class POSFactory implements SalesChannelFactory {

    public DiscountStrategy createDiscount() {
        return new MemberDiscount();
    }

    public PaymentMethod createPayment() {
        return new CashPayment();
    }

    public NotificationService createNotification() {
        return new PrintInvoiceNotification();
    }
}