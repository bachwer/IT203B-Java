package Session07.Ex1;

import java.util.HashMap;
import java.util.Map;

public class Order {
    String orderId;
    Customer customer;
    Map<Product, Integer> items = new HashMap<>();
    double total;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }


    public void addProduct(Product product, int quantity){
        items.put(product, quantity);
    }

    public void setTotal(double total){
        this.total = total;
    }
}
