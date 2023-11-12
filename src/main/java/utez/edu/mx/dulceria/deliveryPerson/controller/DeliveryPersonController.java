package utez.edu.mx.dulceria.deliveryPerson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.deliveryPerson.service.DeliveryPersonService;

@RestController
@RequestMapping("/api/delivery-person")
@CrossOrigin(origins = {"*"})
public class DeliveryPersonController {

    @Autowired
    DeliveryPersonService deliveryPersonService;

    @GetMapping("/")
    public ResponseEntity<Message> getAll() {
        return deliveryPersonService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable Long id) {
        return deliveryPersonService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Message> save(@RequestBody Object object) {
        return deliveryPersonService.save(object);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> update(@PathVariable Long id, @RequestBody Object object) {
        return deliveryPersonService.update(id, object);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable Long id) {
        return deliveryPersonService.delete(id);
    }
}

