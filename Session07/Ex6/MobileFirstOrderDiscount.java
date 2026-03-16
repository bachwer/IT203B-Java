package Session07.Ex6;

class MobileFirstOrderDiscount implements DiscountStrategy {

    public double applyDiscount(double total) {

        System.out.println("Áp dụng giảm giá 15% cho lần đầu");

        return total * 0.85;
    }
}