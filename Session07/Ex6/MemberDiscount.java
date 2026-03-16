package Session07.Ex6;
class MemberDiscount implements DiscountStrategy {

    public double applyDiscount(double total) {

        System.out.println("Áp dụng giảm giá thành viên 5%");

        return total * 0.95;
    }
}