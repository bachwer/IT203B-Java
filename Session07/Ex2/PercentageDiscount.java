package Session07.Ex2;

public class PercentageDiscount implements DiscountStrategy {
    double percent;

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    @Override
    public double applyDiscount(double totalAmount){
        return totalAmount * (1 - percent / 100);
    }
}
