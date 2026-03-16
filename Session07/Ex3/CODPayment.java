package Session07.Ex3;

public class CODPayment implements CODPayable{
    @Override
    public void processCOD(double amount) {
        System.out.println("Xử lý thanh toán COD: " + (int)amount + " - thành công");
    }
}
