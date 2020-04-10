package shop.service;

import org.springframework.data.domain.Sort;
import shop.entity.Producer;

import java.util.List;

public interface ProducerService {
    List<Producer> getAll();
    List<Producer> getAll(Sort sort);
    List<Producer> getAllByProductId(Long id, Sort sort);
    Producer getById(Long id);
    Producer save(Producer producer);
    void deleteById(Long id);

}
