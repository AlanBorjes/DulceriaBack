package utez.edu.mx.dulceria.visits.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.visits.model.Visits;
import utez.edu.mx.dulceria.visits.service.VisitsService;

@RestController
@RequestMapping("/api/visits")
@CrossOrigin(origins = {"*"})
public class VisitsController {

    @Autowired
    VisitsService visitsService;

    @GetMapping("/")
    public ResponseEntity<Message> getAll(){
        return visitsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable Long id){
        return visitsService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Message> save(@RequestBody Visits visits){
        return visitsService.save(visits);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> update(@PathVariable Long id, @RequestBody Visits visits){
        return visitsService.update(id, visits);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable Long id){
        return visitsService.delete(id);
    }
}
