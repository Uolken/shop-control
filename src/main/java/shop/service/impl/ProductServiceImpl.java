package shop.service.impl;

import org.hibernate.Criteria;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import shop.entity.Metric;
import shop.entity.Product;
import shop.repos.MetricRepos;
import shop.repos.ProductRepos;
import shop.service.ProductService;
import shop.service.SettingService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class ProductServiceImpl implements ProductService {

    private final SettingService settingService;

    private final ProductRepos productRepos;
    private final MetricRepos metricRepos;

    public ProductServiceImpl(SettingService settingService, ProductRepos productRepos, MetricRepos metricRepos) {
        this.settingService = settingService;
        this.productRepos = productRepos;
        this.metricRepos = metricRepos;
    }

    @Override
    public Product getById(Long id) {
        return calculateStatus(productRepos.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public Iterable<Product> getAll() {
        return calculateStatus(productRepos.findAll());
    }

    public Iterable<Product> getAll(Sort sort) {
        return calculateStatus(productRepos.findAll(sort));
    }

    @Override
    public Iterable<Product> getAll(PageRequest pageRequest) {
        return calculateStatus(productRepos.findAll(pageRequest));
    }

    @Override
    public Iterable<Product> getAllByRecommendedDate(LocalDate localDate) {
        return productRepos.getAllByRecommendedDate(localDate);
    }

    @Override
    public Product saveProduct(Product product) {
        if (product.getRecommendedDate() == null) {
            product.setRecommendedDate(LocalDate.now().plusDays(Long.parseLong(settingService.getValue("recommendedDays"))));
        }
        return productRepos.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        if (product.getRecommendedDate() == null) {
            product.setRecommendedDate(LocalDate.now().plusDays(Long.parseLong(settingService.getValue("recommendedDays"))));
        }
        return productRepos.save(product);
    }

    @Override
    public Iterable<Metric> getMetrics() {
        return metricRepos.findAll();
    }

    private Product calculateStatus(Product product) {
        if (product.getRecommendedDate() == null) {
            product.setStatus(Product.Status.OK);
            return product;
        }
        long recommendedDays = Long.parseLong(settingService.getValue("recommendedDays"));
        long diff = DAYS.between(LocalDate.now(), product.getRecommendedDate());
        if (diff < 0) {
            product.setStatus(Product.Status.OUTDATED);
        } else if (diff < recommendedDays) {
            product.setStatus(Product.Status.RECOMMENDED);
        } else {
            product.setStatus(Product.Status.OK);
        }
        return product;
    }

    private Iterable<Product> calculateStatus(Iterable<Product> products) {
        final long recommendedDays = Long.parseLong(settingService.getValue("recommendedDays"));
        final LocalDate now = LocalDate.now();
        products.forEach(p -> {
            if (p.getRecommendedDate() == null) {
                p.setStatus(Product.Status.OK);
                return;
            }
            long diff = DAYS.between(now, p.getRecommendedDate());
            if (diff < 0) {
                p.setStatus(Product.Status.OUTDATED);
            } else if (diff < recommendedDays) {
                p.setStatus(Product.Status.RECOMMENDED);
            } else {
                p.setStatus(Product.Status.OK);
            }
        });
        return products;
    }

}
