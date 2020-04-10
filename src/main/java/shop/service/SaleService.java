package shop.service;

import org.springframework.data.domain.Sort;
import shop.entity.Sale;

import java.util.List;

public interface SaleService {

    Sale getById(Long id);

    List<Sale> getAll(Sort sort);

    List<Sale> getAllBySellerId(Long sellerId, Sort sort);

    Sale save(Sale sale);
}
