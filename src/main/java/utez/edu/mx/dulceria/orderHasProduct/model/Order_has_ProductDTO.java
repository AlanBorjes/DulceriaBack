package utez.edu.mx.dulceria.orderHasProduct.model;

import utez.edu.mx.dulceria.order.model.Order;
import utez.edu.mx.dulceria.product.model.Product;

public class Order_has_ProductDTO {
    private long id;
    private int cantidad;
    private Order order;
    private Product product;

    public Order_has_ProductDTO() {
    }

    public Order_has_ProductDTO(long id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    public Order_has_ProductDTO(long id, int cantidad, Order order, Product product) {
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
}
