package utez.edu.mx.dulceria.user.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.dulceria.Utils.EmailService;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.person.model.Person;
import utez.edu.mx.dulceria.person.model.PersonDTO;
import utez.edu.mx.dulceria.rol.model.Rol;
import utez.edu.mx.dulceria.rol.model.RolDTO;
import utez.edu.mx.dulceria.user.DTO.UserDTO;
import utez.edu.mx.dulceria.user.model.User;
import utez.edu.mx.dulceria.user.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"*"})
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    @GetMapping("/")
    public ResponseEntity<Message> getAll(){
        return  userService.findAll();
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Message> getById(@PathVariable("id") long id){
        return  userService.findById(id);
    }

    @GetMapping("/Repatidor/")
    public ResponseEntity<Message> getAllRepatidor(@RequestBody RolDTO rolDTO){
        return  userService.findAllRepatidor(new Rol(rolDTO.getId(), rolDTO.getDescription(), rolDTO.getAcronym()));
    }

    @PostMapping("/")
    public ResponseEntity<Message> createUser(@RequestBody UserDTO createUserDTO) {
        Hibernate.initialize(createUserDTO.getPerson());
        try {
            // Crear un nuevo objeto User usando los datos del DTO y guardar en la base de datos
            User newUser = new User(
                    passwordEncoder.encode(createUserDTO.getPassword()),
                    createUserDTO.getUsername(),
                    createUserDTO.getPerson(),
                    createUserDTO.getStatus(),
                    createUserDTO.getAuthorities()
            );

            // Puedes devolver directamente el nuevo usuario como parte de la respuesta
            return new ResponseEntity<>(new Message("Usuario creado con éxito", false,  userService.save(newUser)), HttpStatus.CREATED);
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir durante la creación del usuario
            return new ResponseEntity<>(new Message("Error al crear el usuario", true, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/password/")
    public ResponseEntity<Message> sendMailContact(@RequestBody UserDTO userDTO,
                                                   BindingResult result){
        if(result.hasErrors())
            return new ResponseEntity<>(new Message("Verifique los datos", true, null),
                    HttpStatus.BAD_REQUEST);
        if(emailService.sendEmail(userDTO)){
            return  new ResponseEntity<>(new Message("Correo enviado correctamente", false, userDTO),
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new Message("Error al enviar el correo", true, userDTO),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
