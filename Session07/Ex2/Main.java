package Session07.Ex2;
public class Main {

    public static void main(String[] args) {

        double total = 1000000;

        // PercentageDiscount 10%
        DiscountStrategy percentDiscount = new PercentageDiscount(10);
        OrderCalculator calculator1 = new OrderCalculator(percentDiscount);

        double result1 = calculator1.calculateTotal(total);
        System.out.println("Số tiền sau giảm: " + (int) result1);

        // FixedDiscount 50.000
        DiscountStrategy fixedDiscount = new FixedDiscount(50000);
        OrderCalculator calculator2 = new OrderCalculator(fixedDiscount);

        double result2 = calculator2.calculateTotal(total);
        System.out.println("Số tiền sau giảm: " + (int) result2);

        // NoDiscount
        DiscountStrategy noDiscount = new NoDiscount();
        OrderCalculator calculator3 = new OrderCalculator(noDiscount);

        double result3 = calculator3.calculateTotal(total);
        System.out.println("Số tiền sau giảm: " + (int) result3);

        // HolidayDiscount 15%
        DiscountStrategy holidayDiscount = new HolidayDiscount();
        OrderCalculator calculator4 = new OrderCalculator(holidayDiscount);

        double result4 = calculator4.calculateTotal(total);
        System.out.println("Số tiền sau giảm: " + (int) result4);
    }
}