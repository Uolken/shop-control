package shop.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop.entity.Sale;
import shop.repos.SaleRepos;
import shop.service.SaleService;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepos saleRepos;

    @Override
    public Sale getById(Long id) {
        return saleRepos.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Sale> getAll(Sort sort) {
        return saleRepos.findAll(sort);
    }

    @Override
    public List<Sale> getAllBySellerId(Long sellerId, Sort sort) {
        return saleRepos.findBySeller_id(sellerId, sort);
    }

    @Override
    public Sale save(Sale sale) {
        return saleRepos.save(sale);
    }

    public SaleServiceImpl(SaleRepos saleRepos) {
        this.saleRepos = saleRepos;
    }
}
