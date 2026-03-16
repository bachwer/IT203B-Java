package Session07.Ex5;

public class PercentageDiscount implements DiscountStrategy{

    double percent;

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    public double applyDiscount(double total) {
        return total * (1 - percent / 100);
    }
}
