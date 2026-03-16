package Session07.Ex2;

public class OrderCalculator {
    DiscountStrategy discountStrategy;

    public OrderCalculator(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double calculateTotal(double totalAmount){
        return discountStrategy.applyDiscount(totalAmount);
    }

}
