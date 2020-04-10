package shop.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer timeForDelivery;
    @Embedded
    private Address address;
    private Integer distance;
    private Integer price;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Product product;

    public Producer(Long id, String name, Integer timeForDelivery, Address address, Integer distance, Integer price, Product product) {
        this.id = id;
        this.name = name;
        this.timeForDelivery = timeForDelivery;
        this.address = address;
        this.distance = distance;
        this.price = price;
        this.product = product;
    }

    public Producer(String name, Integer timeForDelivery, Address address, Integer distance, Integer price, Product product) {
        this(null, name, timeForDelivery, address, distance, price, product);
    }

    public Producer() {
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

    public Integer getTimeForDelivery() {
        return timeForDelivery;
    }

    public void setTimeForDelivery(Integer timeForDelivery) {
        this.timeForDelivery = timeForDelivery;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
