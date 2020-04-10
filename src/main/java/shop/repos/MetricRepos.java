package shop.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shop.entity.Metric;

@Repository
public interface MetricRepos extends CrudRepository<Metric, Long> {
}
