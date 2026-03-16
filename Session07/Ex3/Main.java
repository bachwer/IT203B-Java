package Session07.Ex3;
public class Main {

    public static void main(String[] args) {

        PaymentProcessor processor = new PaymentProcessor();

        // COD
        CODPayment cod = new CODPayment();
        processor.processPayment(cod, 500000);

        // Credit Card
        CreditCardPayment card = new CreditCardPayment();
        processor.processPayment(card, 1000000);

        // MoMo
        MomoPayment momo = new MomoPayment();
        processor.processPayment(momo, 750000);

        // Kiểm tra LSP
        PaymentMethod payment = new CreditCardPayment();
        processor.processPayment(payment, 1000000);

        payment = new MomoPayment();
        processor.processPayment(payment, 750000);
    }
}