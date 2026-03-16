package Session07.Ex5;
import java.util.*;

class DatabaseOrderRepository implements OrderRepository {

    List<Order> orders = new ArrayList<>();

    public void save(Order order) {

        orders.add(order);

        System.out.println("Lưu đơn hàng vào database: " + order.orderId);
    }

    public List<Order> findAll() {
        return orders;
    }
}