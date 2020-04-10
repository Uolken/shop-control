package shop.repos;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.entity.Batch;
import shop.entity.Order;

import java.util.List;
import java.util.Map;

@Repository
public interface BatchRepos extends JpaRepository<Batch, Long> {
    List<Batch> findAllByProduct_id(Long id, Sort sort);
    List<Batch> findByConditionIsSpoiled(Boolean conditionIsSpoiled, Sort sort);

}
