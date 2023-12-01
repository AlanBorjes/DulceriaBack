package utez.edu.mx.dulceria.orderHasProduct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.order.model.OrderDTO;
import utez.edu.mx.dulceria.orderHasProduct.model.Order_has_Product;
import utez.edu.mx.dulceria.orderHasProduct.model.Order_has_ProductDTO;
import utez.edu.mx.dulceria.orderHasProduct.service.OrderHasProductService;
import utez.edu.mx.dulceria.rol.model.Rol;
import utez.edu.mx.dulceria.rol.model.RolDTO;

@RestController
@RequestMapping("/api/ordershasproduct")
@CrossOrigin(origins = {"*"})
public class OrderHasProductController {

    @Autowired
    private OrderHasProductService orderHasProductService;

    @GetMapping("/")
    public ResponseEntity<Message> getAllOrdersHasProduct() {
        return orderHasProductService.findAllorderHasProduct();
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Message> getById(@PathVariable("id") long id){
        return  orderHasProductService.findById(id);
    }
    @PostMapping("/")
    public  ResponseEntity<Message> save(@RequestBody Order_has_ProductDTO rder_has_ProductDTO){
        return  orderHasProductService.save(new Order_has_Product(rder_has_ProductDTO.getOrder(),rder_has_ProductDTO.getProduct(),rder_has_ProductDTO.getCantidad()));
    }

    @PutMapping("/")
    public  ResponseEntity<Message> update(@RequestBody Order_has_ProductDTO rder_has_ProductDTO){
        return  orderHasProductService.update(new Order_has_Product(rder_has_ProductDTO.getId(),rder_has_ProductDTO.getOrder(),rder_has_ProductDTO.getProduct(),rder_has_ProductDTO.getCantidad()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteOrder(@PathVariable long id) {
        return orderHasProductService.delete(id);
    }
}
