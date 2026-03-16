package Session07.Ex5;
class MomoPayment implements PaymentMethod {

    public void pay(double amount) {
        System.out.println("Thanh toán MoMo: " + amount);
    }
}