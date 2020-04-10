package shop.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import shop.entity.Producer;
import shop.repos.ProducerRepos;
import shop.service.ProducerService;

import java.util.List;

@Service
public class ProducerServiceImpl implements ProducerService {

    private final ProducerRepos producerRepos;

    public ProducerServiceImpl(ProducerRepos productRepos) {
        this.producerRepos = productRepos;
    }

    @Override
    public Producer getById(Long id) {
        return producerRepos.findById(id).get();
    }

    @Override
    public Producer save(Producer producer) {
        return producerRepos.save(producer);
    }

    @Override
    public void deleteById(Long id) {
        producerRepos.deleteById(id);
    }

    @Override
    public List<Producer> getAll() {
        return (List<Producer>) producerRepos.findAll();
    }

    @Override
    public List<Producer> getAll(Sort sort) {
        return producerRepos.findAll(sort);
    }

    @Override
    public List<Producer> getAllByProductId(Long id, Sort sort) {
        return producerRepos.findByProduct_id(id, sort);
    }

}
