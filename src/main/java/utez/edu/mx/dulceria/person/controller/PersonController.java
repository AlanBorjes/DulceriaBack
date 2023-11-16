package utez.edu.mx.dulceria.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.person.model.Person;
import utez.edu.mx.dulceria.person.model.PersonDTO;
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
    public ResponseEntity<Message> save(@RequestBody PersonDTO PersonDTO){
        return  personService.save(new Person(PersonDTO.getName(), PersonDTO.getLastName(),PersonDTO.getAddress(),PersonDTO.getPhone(),PersonDTO.getEmail(),PersonDTO.getEdad(),PersonDTO.getSexo(),PersonDTO.getOwner(),PersonDTO.getDeliver()));
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