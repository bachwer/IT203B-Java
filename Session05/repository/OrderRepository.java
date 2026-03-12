package Session05.repository;

import Session05.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository {

    private List<Order> orders = new ArrayList<>();

    // Lưu đơn hàng
    public void save(Order order) {
        orders.add(order);
    }

    // Lấy tất cả đơn
    public List<Order> findAll() {
        return orders;
    }

    // Tìm đơn theo ID
    public Optional<Order> findById(String orderId) {
        return orders.stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst();
    }

    // Xóa đơn
    public boolean deleteById(String orderId) {
        return orders.removeIf(order -> order.getOrderId().equals(orderId));
    }
}