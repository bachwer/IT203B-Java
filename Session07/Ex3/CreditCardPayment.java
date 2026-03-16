package Session07.Ex3;

public class CreditCardPayment implements CardPayable{
    @Override
    public void processCreditCard(double amount) {
        System.out.println("Xử lý thanh toán thẻ tín dụng: " + (int)amount + " - Thành công");
    }
}
