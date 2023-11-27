package utez.edu.mx.dulceria.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.dulceria.Utils.EmailService;
import utez.edu.mx.dulceria.Utils.Message;
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


    @PostMapping("/")
    public  ResponseEntity<Message> saveUsersave(@RequestBody UserDTO userDTO){
        return  userService.save(new User(passwordEncoder.encode(userDTO.getPassword()),userDTO.getUsername(),userDTO.getPerson(),userDTO.getStatus(),userDTO.getAuthorities()));
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
