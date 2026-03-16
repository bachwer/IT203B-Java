package Session07.Ex5;

import java.util.ArrayList;
import java.util.List;

public class Order {
    String orderId;
    Customer customer;
    List<OrderItem> items = new ArrayList<>();
    double totalAmount;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public void addItem(OrderItem item){
        items.add(item);
    }

    public double CalculateTotal(){
        double total = 0;

        for(OrderItem item: items){
            total += item.getTotal();
        }
        return total;
    }



}
