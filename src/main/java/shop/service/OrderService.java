package shop.service;

import org.springframework.data.domain.Sort;
import shop.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll();
    List<Order> getAll(Sort sort);
    List<Order> getAllByProductId(Long productId, Sort sort);
    Order save(Order order);
    void deleteById(Long id);
}
