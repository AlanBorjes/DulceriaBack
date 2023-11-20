package utez.edu.mx.dulceria.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.order.model.Order;
import utez.edu.mx.dulceria.order.model.OrderDTO;
import utez.edu.mx.dulceria.order.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = {"*"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<Message> getAllOrders() {
        return orderService.findAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getOrderById(@PathVariable long id) {
        return orderService.findOrderById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Message> saveOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.saveOrder(orderDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateOrder(@PathVariable long id, @RequestBody OrderDTO updatedOrderDTO) {
        return orderService.updateOrder(id, updatedOrderDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteOrder(@PathVariable long id) {
        return orderService.deleteOrder(id);
    }
}
