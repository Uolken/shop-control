package shop.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop.entity.Batch;
import shop.repos.BatchRepos;
import shop.service.BatchService;

import java.util.List;

@Service
public class BatchServiceImpl implements BatchService {

    private final BatchRepos batchRepos;

    public BatchServiceImpl(BatchRepos batchRepos) {
        this.batchRepos = batchRepos;
    }

    @Override
    public List<Batch> getAllByProductId(Long id, Sort sort) {
        return batchRepos.findAllByProduct_id(id, sort);
    }

    @Override
    public List<Batch> getNotSpoiled(Sort sort) {
        return batchRepos.findByConditionIsSpoiled(false, sort);
    }

    @Override
    public List<Batch> getAll(Sort sort) {
        return batchRepos.findAll(sort);
    }

    @Override
    public void deleteById(Long id) {
        batchRepos.deleteById(id);
    }

    @Override
    public Batch save(Batch batch) {
        return batchRepos.save(batch);
    }
}
