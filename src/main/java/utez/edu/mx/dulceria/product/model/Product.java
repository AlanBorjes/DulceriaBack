package utez.edu.mx.dulceria.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import utez.edu.mx.dulceria.orderHasProduct.model.Order_has_Product;

import javax.persistence.*;
import java.util.List;

@Entity

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column()
    private String name;

    @Column()
    private String description;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Order_has_Product> orderList;

    public Product() {
    }

    public Product(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
