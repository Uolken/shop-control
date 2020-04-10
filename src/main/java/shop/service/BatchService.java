package shop.service;

import org.springframework.data.domain.Sort;
import shop.entity.Batch;

import java.util.List;

public interface BatchService {
    List<Batch> getAllByProductId(Long id, Sort sort);
    List<Batch> getNotSpoiled(Sort sort);
    List<Batch> getAll(Sort sort);
    void deleteById(Long id);
    Batch save(Batch batch);
}
