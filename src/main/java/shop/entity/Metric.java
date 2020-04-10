package shop.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    public Metric(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Metric(String name) {
        this(null, name);
    }

    public Metric() {
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
}
