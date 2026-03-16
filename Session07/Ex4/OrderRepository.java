package Session07.Ex4;

import java.util.List;

interface OrderRepository {
    void save(Order order);
    List<Order> findAll();

}
