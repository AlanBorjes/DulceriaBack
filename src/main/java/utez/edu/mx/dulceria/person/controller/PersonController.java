package utez.edu.mx.dulceria.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.person.service.PersonService;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = {"*"})
public class PersonController {
    
    @Autowired
    PersonService personService;

    @GetMapping("/")
    public ResponseEntity<Message> getAll(){
        return  personService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable Long id){
        return  personService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Message> save(@RequestBody Object object){
        return  personService.save(object);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> update(@PathVariable Long id, @RequestBody Object object){
        return  personService.update(id,object);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable Long id){
        return  personService.delete(id);
    }

    
}