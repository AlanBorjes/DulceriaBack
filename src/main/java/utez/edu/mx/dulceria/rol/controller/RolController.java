package utez.edu.mx.dulceria.rol.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.rol.model.Rol;
import utez.edu.mx.dulceria.rol.model.RolDTO;
import utez.edu.mx.dulceria.rol.service.RolService;

@RestController
@RequestMapping("/api/rol")
@CrossOrigin(origins = {"*"})
public class RolController {
    @Autowired
    RolService rolService;
    @GetMapping("/")
    public ResponseEntity<Message> getAll(){
        return  rolService.findAll();
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Message> getById(@PathVariable("id") long id){
        return  rolService.findById(id);
    }
    @PostMapping("/")
    public  ResponseEntity<Message> save(@RequestBody RolDTO rolDTO){
        return  rolService.save(new Rol(rolDTO.getAcronym(),rolDTO.getDescription()));
    }

    @PutMapping("/")
    public  ResponseEntity<Message> update(@RequestBody RolDTO rolDTO){
        return  rolService.update(new Rol(rolDTO.getId(),rolDTO.getAcronym(),rolDTO.getDescription()));
    }

}
