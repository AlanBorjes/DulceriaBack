package utez.edu.mx.dulceria.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.Utils.SaveImage;
import utez.edu.mx.dulceria.order.model.Order;
import utez.edu.mx.dulceria.order.model.OrderDTO;
import utez.edu.mx.dulceria.order.service.OrderService;
import utez.edu.mx.dulceria.person.model.PersonDTO;
import utez.edu.mx.dulceria.product.model.Product;
import utez.edu.mx.dulceria.statusOrder.model.Status_order;
import utez.edu.mx.dulceria.visit.model.Visit;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = {"*"})
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    SaveImage saveImage;

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
    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestParam("incidencia") MultipartFile multipartFile, @RequestParam("description") String description, @RequestParam("observaciones") String observaciones, @RequestParam("status") Status_order status,@RequestParam("visit") Visit visit){
        return orderService.saveOrder(new OrderDTO(description, observaciones, saveImage.upload(multipartFile), status,visit));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Message> update(@RequestBody OrderDTO orderDTO){
        return orderService.update(new Order(orderDTO.getId(),orderDTO.getDescription(),orderDTO.getObservaciones(),orderDTO.getStatus()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteOrder(@PathVariable long id) {
        return orderService.deleteOrder(id);
    }
}
