package shop.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Batch batch;

    @ManyToOne
    private Sale sale;

    @NotNull
    private Long count;

    @NotNull
    private Long price;

    public SaleItem(Long id, Batch batch, Long count, Long price) {
        this.id = id;
        this.batch = batch;
        this.count = count;
        this.price = price;
    }

    public SaleItem(Batch batch, Long count, Long price) {
        this(null, batch, count, price);
    }

    public SaleItem() {
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
