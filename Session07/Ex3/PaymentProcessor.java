package Session07.Ex3;

public class PaymentProcessor {
    public void processPayment(PaymentMethod paymentMethod, double amount){
        if(paymentMethod instanceof CODPayable){
            ((CODPayable) paymentMethod).processCOD(amount);
        }else if(paymentMethod instanceof CardPayable){
            ((CardPayable) paymentMethod).processCreditCard(amount);
        }
        else if(paymentMethod instanceof EWalletPayable){
            ((EWalletPayable) paymentMethod ).processMomo(amount);
        }
    }
}
