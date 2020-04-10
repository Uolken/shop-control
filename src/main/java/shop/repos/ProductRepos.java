package shop.repos;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shop.entity.Product;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepos extends JpaRepository<Product, Long> {
    Iterable<Product> getAllByRecommendedDate(LocalDate localDate);

}
