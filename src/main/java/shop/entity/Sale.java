package shop.entity;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "sale",
            cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    private Set<SaleItem> saleItems;

    @NotNull
    private LocalDate date;

    @NotNull
    @ManyToOne
    private Seller seller;

    @Formula("SELECT COUNT(*) FROM sale_item si WHERE si.sale_id = id")
    private Long countOfItem;

    @Formula("COALESCE((SELECT SUM(si.count * si.price) FROM sale_item si WHERE si.sale_id = id), 0)")
    private Long sum;

    public Sale(Long id, Set<SaleItem> saleItems, LocalDate date, Seller seller) {
        this.id = id;
        this.saleItems = saleItems;
        this.date = date;
        this.seller = seller;
    }

    public Sale(Set<SaleItem> saleItems, LocalDate date, Seller seller) {
        this(null, saleItems, date, seller);
    }

    public Sale() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(Set<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Long getCountOfItem() {
        return countOfItem;
    }

    public void setCountOfItem(Long countOfItem) {
        this.countOfItem = countOfItem;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }
}
