package utez.edu.mx.dulceria.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.store.model.Store;
import utez.edu.mx.dulceria.store.model.StoreDTO;
import utez.edu.mx.dulceria.store.service.StoreService;

@RestController
@RequestMapping("/api/store")
@CrossOrigin(origins = {"*"})
public class StoreController {
    
    @Autowired
    StoreService storeService;

    @GetMapping("/")
    public ResponseEntity<Message> getAll(){
        return  storeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable Long id){
        return  storeService.findById(id);
    }

    @GetMapping("/deliver/{id}")
    public ResponseEntity<Message> getByDeliver(@PathVariable Long id){
        return  storeService.findByDeliver(id);
    }
    @PostMapping("/")
    public ResponseEntity<Message> save(@RequestBody StoreDTO object){
        return  storeService.save(new Store(object.getName(),object.getAddress(),object.getRfc(),object.getOwner(),object.getDeliver()));
    }

    @PutMapping("/")
    public ResponseEntity<Message> update(@RequestBody StoreDTO object){
        return  storeService.update(new Store(object.getId(),object.getName(),object.getAddress(),object.getRfc(),object.getOwner(),object.getDeliver()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable Long id){
        return  storeService.delete(id);
    }


}