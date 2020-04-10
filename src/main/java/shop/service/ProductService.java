package shop.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import shop.entity.Metric;
import shop.entity.Product;

import java.time.LocalDate;

public interface ProductService {
    Product getById(Long id);

    Iterable<Product> getAll();

    Iterable<Product> getAll(Sort sort);

    Iterable<Product> getAll(PageRequest pageRequest);

    Iterable<Product> getAllByRecommendedDate(LocalDate localDate);

    Product saveProduct(Product product);

    Product updateProduct(Product product);

    Iterable<Metric> getMetrics();
}
