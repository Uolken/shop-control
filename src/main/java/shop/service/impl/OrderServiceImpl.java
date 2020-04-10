package shop.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import shop.entity.Order;
import shop.repos.OrderRepos;
import shop.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    final OrderRepos orderRepos;

    @Override
    public List<Order> getAll() {
        return orderRepos.findAll();
    }

    @Override
    public List<Order> getAll(Sort sort) {
        return orderRepos.findAll(sort);
    }

    @Override
    public List<Order> getAllByProductId(Long productId, Sort sort) {
        return orderRepos.findAllByProduct_id(productId, sort);
    }

    @Override
    public Order save(Order order) {
        return orderRepos.save(order);
    }

    @Override
    public void deleteById(Long id) {
        orderRepos.deleteById(id);
    }

    public OrderServiceImpl(OrderRepos orderRepos) {
        this.orderRepos = orderRepos;
    }
}
