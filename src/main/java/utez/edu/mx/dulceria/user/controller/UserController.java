package utez.edu.mx.dulceria.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.user.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"*"})
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public ResponseEntity<Message> getAll(){
        return  userService.findAll();
    }
}
