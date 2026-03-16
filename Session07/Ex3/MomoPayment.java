package Session07.Ex3;

public class MomoPayment implements EWalletPayable{

    @Override
    public void processMomo(double amount) {
        System.out.println("Xử lý thanh toán MoMo: " + (int)amount + " - Thành công");

    }
}
