package shop.repos;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shop.entity.Order;

import java.util.List;

@Repository
public interface OrderRepos extends JpaRepository<Order, Long> {
    List<Order> findAllByProduct_id(Long id, Sort sort);
}
