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
    private String name;
    private String description;
    private double price;
    private String image;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Order_has_Product> orderList;

    public Product() {
    }

    public Product(long id, String name, String description, double price, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public Product(String name, String description, double price, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Order_has_Product> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order_has_Product> orderList) {
        this.orderList = orderList;
    }
}
