package shop.entity;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Product {
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {OK, RECOMMENDED, OUTDATED}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 255)
    private String name;

    @NotNull
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private Metric metric;

    @Formula("COALESCE((SELECT sum( b.count) - COALESCE(sum(si.count), 0)FROM batch b LEFT JOIN sale_item si ON b.id = si.batch_id WHERE b.expiration_date >= CURRENT_DATE() AND b.product_id = id GROUP BY b.product_id), 0)")
    private Long count;

    @NotNull
    @Min(0)
    private Long minimalRecommendedAmount;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "product",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Producer> producers;

    @OneToMany(mappedBy = "product" ,cascade = CascadeType.REMOVE)
    private Set<Batch> batches;

    @NotNull
    private LocalDate recommendedDate;

    @Formula("COALESCE((SELECT COUNT(1) > 0 FROM ord o WHERE o.product_id = id), false)")
    private Boolean inOrder;

    @Transient
    private Product.Status status;


    public Product(Long id,
                   String name,
                   Metric metric,
                   Long minimalRecommendedAmount,
                   Set<Producer> producers,
                   Set<Batch> batches,
                   LocalDate recommendedDate) {
        this.id = id;
        this.name = name;
        this.metric = metric;
        this.count = 0L;
        this.minimalRecommendedAmount = minimalRecommendedAmount;
        this.producers = producers;
        this.batches = batches;
        this.recommendedDate = recommendedDate;
    }

    public Product(String name,
                   Metric metric,
                   Long minimalRecommendedAmount,
                   Set<Producer> producers,
                   Set<Batch> batches,
                   LocalDate recommendedDate) {
        this(null, name, metric, minimalRecommendedAmount, producers, batches, recommendedDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
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

    public Set<Producer> getProducers() {
        return producers;
    }

    public void setProducers(Set<Producer> producers) {
        this.producers = producers;
    }

    public Set<Batch> getBatches() {
        return batches;
    }

    public void setBatches(Set<Batch> batches) {
        this.batches = batches;
    }

    public LocalDate getRecommendedDate() {
        return recommendedDate;
    }

    public void setRecommendedDate(LocalDate recommendedDate) {
        this.recommendedDate = recommendedDate;
    }

    public Boolean getInOrder() {
        return inOrder;
    }

    public void setInOrder(Boolean inOrder) {
        this.inOrder = inOrder;
    }
}
