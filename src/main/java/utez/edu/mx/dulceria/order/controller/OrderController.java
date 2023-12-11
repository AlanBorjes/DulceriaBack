package utez.edu.mx.dulceria.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.Utils.SaveImage;
import utez.edu.mx.dulceria.order.model.Order;
import utez.edu.mx.dulceria.order.model.OrderDTO;
import utez.edu.mx.dulceria.order.service.OrderService;
import utez.edu.mx.dulceria.person.model.PersonDTO;
import utez.edu.mx.dulceria.product.model.Product;
import utez.edu.mx.dulceria.statusOrder.model.StatusOrderRepository;
import utez.edu.mx.dulceria.statusOrder.model.Status_order;
import utez.edu.mx.dulceria.visit.model.Visit;
import utez.edu.mx.dulceria.visit.repository.VisitRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = {"*"})
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    SaveImage saveImage;
    @Autowired
    private StatusOrderRepository statusOrderRepository;
    @Autowired
    private VisitRepository visitRepository;
    @GetMapping("/")
    public ResponseEntity<Message> getAllOrders() {
        return orderService.findAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getOrderById(@PathVariable long id) {
        return orderService.findOrderById(id);
    }

    @GetMapping("/visit/{id}")
    public ResponseEntity<Message> getByVisit(@PathVariable long id){
        return orderService.findByVisit(id);
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

    @PutMapping("/incidencia/")
    public ResponseEntity<Message> updateIncidencia(@RequestParam("incidencia") MultipartFile multipartFile, @RequestParam("id") String id,@RequestParam("description") String description, @RequestParam("observaciones") String observaciones, @RequestParam("status") String status,@RequestParam("visit") String visit){

        return orderService.update(new Order(Long.parseLong(id),description,observaciones,saveImage.upload(multipartFile),getByOrderVisit(Long.parseLong(status)),getByVisit(Long.parseLong(visit))));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteOrder(@PathVariable long id) {
        return orderService.deleteOrder(id);
    }

    @Transactional(readOnly = true)
    public Status_order getByOrderVisit(long id){
        return statusOrderRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Visit getByVisit(long id){
        return visitRepository.findById(id);
    }
}
