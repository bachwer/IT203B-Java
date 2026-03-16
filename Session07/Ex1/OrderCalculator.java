package Session07.Ex1;

public class OrderCalculator
{
    public double calculateTotal(Order order){
        double total = 0;
        for(Product p: order.items.keySet()){
            int quantity = order.items.get(p);
                total += p.price * quantity;
        }
        return total;
    }
}
