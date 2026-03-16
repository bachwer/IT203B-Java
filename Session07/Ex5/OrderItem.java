package Session07.Ex5;

public class OrderItem {
    Product product;
    int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal(){
        return product.price * quantity;
    }
}
