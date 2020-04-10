package shop.entity;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    private Product product;

    @NotNull
    private LocalDate productionDate;

    @NotNull
    private LocalDate expirationDate;

    @NotNull
    private Long count;

    @Formula("COALESCE((SELECT SUM(si.count) FROM SALE_ITEM si WHERE si.batch_id = id GROUP BY si.batch_id), 0) ")
    private Long soldCount;

    @Formula("(SELECT ((SELECT b.count FROM batch b WHERE b.id = id) - (COALESCE((SELECT SUM(si.count) FROM SALE_ITEM si WHERE si.batch_id = id GROUP BY si.batch_id), 0)) ) )")
    private Long countLeft;

    @Formula("(SELECT (b.expiration_date < CURRENT_DATE()) FROM batch b WHERE b.id = id)")
    private Boolean conditionIsSpoiled;

    @OneToMany(mappedBy = "batch")
    private Set<SaleItem> saleItems;


    public Batch(Product product, LocalDate productionDate, LocalDate expirationDate, Long count) {
        this.product = product;
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;
        this.count = count;

    }

    public Batch() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
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


    public Set<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(Set<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public Long getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(Long soldCount) {
        this.soldCount = soldCount;
    }

    public Boolean getConditionIsSpoiled() {
        return conditionIsSpoiled;
    }

    public void setConditionIsSpoiled(Boolean conditionIsSpoiled) {
        this.conditionIsSpoiled = conditionIsSpoiled;
    }

    public Long getCountLeft() {
        return countLeft;
    }

    public void setCountLeft(Long countLeft) {
        this.countLeft = countLeft;
    }
}
