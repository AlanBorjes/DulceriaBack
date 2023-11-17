package utez.edu.mx.dulceria.visit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.visit.model.Visit;
import utez.edu.mx.dulceria.visit.model.VisitDTO;
import utez.edu.mx.dulceria.visit.service.VisitService;

@RestController
@RequestMapping("/api/visit")
@CrossOrigin(origins = {"*"})
public class VisitController {

    @Autowired
    VisitService visitService;

    @GetMapping("/")
    public ResponseEntity<Message> getAll(){
        return  visitService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable Long id){
        return  visitService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Message> save(@RequestBody VisitDTO object){
        return  visitService.save(new Visit(object.getId(),object.getDay_visit(),object.getStatus(),object.getStore()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> update(@RequestBody VisitDTO object){
        return  visitService.update(new Visit(object.getId(),object.getDay_visit(),object.getStatus(),object.getStore()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable Long id){
        return  visitService.delete(id);
    }


}