package Session07.Ex6;
class WebsiteDiscount implements DiscountStrategy {

    public double applyDiscount(double total) {

        System.out.println("Áp dụng giảm giá 10% cho đơn hàng website");

        return total * 0.9;
    }
}