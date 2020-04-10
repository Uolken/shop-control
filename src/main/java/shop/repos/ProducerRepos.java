package shop.repos;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shop.entity.Producer;

import java.util.List;

@Repository
public interface ProducerRepos extends JpaRepository<Producer, Long> {
    List<Producer> findByProduct_id(Long id, Sort sort);
}
