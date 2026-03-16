package Session07.Ex5;
import java.util.*;

class FileOrderRepository implements OrderRepository {

    List<Order> orders = new ArrayList<>();

    public void save(Order order) {

        orders.add(order);

        System.out.println("Đã lưu đơn hàng " + order.orderId);
    }

    public List<Order> findAll() {
        return orders;
    }
}