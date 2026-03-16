package Session07.Ex5;
import java.util.List;

interface OrderRepository {

    void save(Order order);

    List<Order> findAll();
}