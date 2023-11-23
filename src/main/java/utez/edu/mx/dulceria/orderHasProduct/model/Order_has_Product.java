package utez.edu.mx.dulceria.orderHasProduct.model;

import utez.edu.mx.dulceria.order.model.Order;
import utez.edu.mx.dulceria.product.model.Product;
import utez.edu.mx.dulceria.store.model.Store;

import javax.persistence.*;

@Entity
public class Order_has_Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int cantidad;

    @ManyToOne()
    @JoinColumn(name = "order_id", nullable=false)
    private Order order;

    @ManyToOne()
    @JoinColumn(name = "product_id", nullable=false)
    private Product product;

    public Order_has_Product() {
    }

    public Order_has_Product(long id, Order order, Product product) {
        this.id = id;
        this.order = order;
        this.product = product;
    }

    public Order_has_Product(long id, int cantidad, Order order, Product product) {
        this.id = id;
        this.cantidad = cantidad;
        this.order = order;
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
