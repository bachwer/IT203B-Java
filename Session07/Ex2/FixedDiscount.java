package Session07.Ex2;

public class FixedDiscount implements DiscountStrategy {
    double amount;


    public FixedDiscount(double amount) {
        this.amount = amount;
    }

    @Override
    public double applyDiscount(double totalAmount){
        return totalAmount - amount;
    }
}
