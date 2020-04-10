package shop.entity.dto;

import shop.entity.Product;

import java.time.LocalDate;

public class ProductResponse {
    private Long id;
    private String name;
    private LocalDate productionDate;
    private LocalDate expirationDate;
    private String metric;
    private Long count;
    private Long minimalRecommendedAmount;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.metric = product.getMetric().getName();
        this.count = product.getCount();
        this.minimalRecommendedAmount = product.getMinimalRecommendedAmount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getMinimalRecommendedAmount() {
        return minimalRecommendedAmount;
    }

    public void setMinimalRecommendedAmount(Long minimalRecommendedAmount) {
        this.minimalRecommendedAmount = minimalRecommendedAmount;
    }
}
