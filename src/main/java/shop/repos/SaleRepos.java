package shop.repos;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shop.entity.Sale;

import java.util.List;

@Repository
public interface SaleRepos extends JpaRepository<Sale, Long> {
    List<Sale> findBySeller_id(Long id, Sort sort);
}
