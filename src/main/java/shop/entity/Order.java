package shop.entity;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "ord")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private Product product;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private Producer producer;

    @NotNull
    private LocalDate orderDate;

    @Nullable
    private LocalDate arriveDate;

    @NotNull
    private Long countOfProduct;

    @NotNull
    private Long price;

    @NotNull
    private Boolean stateIsArrived;


    public Order(Long id, Product product, Producer producer, LocalDate orderDate, Long countOfProduct, Long price, Boolean stateIsArrived) {
        this.id = id;
        this.product = product;
        this.producer = producer;
        this.orderDate = orderDate;
        this.countOfProduct = countOfProduct;
        this.price = price;
        this.stateIsArrived = stateIsArrived;
    }

    public Order(Product product, Producer producer, LocalDate orderDate, Long countOfProduct, Long price, Boolean stateIsArrived) {
        this(null, product, producer, orderDate, countOfProduct, price, stateIsArrived);
    }

    public Order() {

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

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Long getCountOfProduct() {
        return countOfProduct;
    }

    public void setCountOfProduct(Long countOfProduct) {
        this.countOfProduct = countOfProduct;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Boolean getStateIsArrived() {
        return stateIsArrived;
    }

    public void setStateIsArrived(Boolean stateIsArrived) {
        this.stateIsArrived = stateIsArrived;
    }

    @Nullable
    public LocalDate getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(@Nullable LocalDate arriveDate) {
        this.arriveDate = arriveDate;
    }
}
