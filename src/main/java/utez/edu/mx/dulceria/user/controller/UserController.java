package utez.edu.mx.dulceria.user.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utez.edu.mx.dulceria.Utils.EmailService;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.Utils.SaveImage;
import utez.edu.mx.dulceria.person.model.Person;
import utez.edu.mx.dulceria.person.model.PersonDTO;
import utez.edu.mx.dulceria.person.repository.PersonRepository;
import utez.edu.mx.dulceria.rol.model.Rol;
import utez.edu.mx.dulceria.rol.model.RolDTO;
import utez.edu.mx.dulceria.rol.repository.RolRepository;
import utez.edu.mx.dulceria.user.DTO.UserDTO;
import utez.edu.mx.dulceria.user.model.User;
import utez.edu.mx.dulceria.user.service.UserService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"*"})
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    SaveImage saveImage;
    @Autowired
    EmailService emailService;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    RolRepository rolRepository;
    @GetMapping("/")
    public ResponseEntity<Message> getAll(){
        return  userService.findAll();
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Message> getById(@PathVariable("id") long id){
        return  userService.findById(id);
    }

    @GetMapping("/Repatidor/{id}")
    public ResponseEntity<Message> getAllRepatidor(@PathVariable("id") long id){
        return  userService.findAllRepatidor(new Rol(id));
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

    @PostMapping("/saveImage")
    public ResponseEntity<Message> save (@RequestParam("image")
    MultipartFile image,@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("person") String person,@RequestParam("code") String code,@RequestParam("status") String status,@RequestParam("rol") String rol){
        Person personTemp = personRepository.getById(Long.valueOf(person));
        Long rolnew = Long.valueOf(rol);
        Set<Rol> roles = new HashSet<>(); // Inicializar roles como un conjunto
        System.out.println(roles);

        if (rolnew == 1) {
            Rol rol1 = rolRepository.getById(rolnew);
            roles.add(rol1);
        } else if (rolnew == 2) {
            Rol rol2 = rolRepository.getById(rolnew);
            roles.add(rol2);
        } else {
            Rol rol1 = rolRepository.getById(1L);
            Rol rol2 = rolRepository.getById(2L);
            roles.add(rol1);
            roles.add(rol2);
        }

        System.out.println(roles);
        UserDTO createUserDTO = new UserDTO(username,password,code, Integer.parseInt(status),personTemp);
        try {
            // Crear un nuevo objeto User usando los datos del DTO y guardar en la base de datos
            User newUser = new User(
                    passwordEncoder.encode(createUserDTO.getPassword()),
                    saveImage.upload(image),
                    createUserDTO.getUsername(),
                    createUserDTO.getPerson(),
                    createUserDTO.getStatus(),
                    roles
            );
            // Puedes devolver directamente el nuevo usuario como parte de la respuesta
            return new ResponseEntity<>(new Message("Usuario creado con éxito", false,  userService.save2(newUser, Long.parseLong(rol))), HttpStatus.CREATED);
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir durante la creación del usuario
            return new ResponseEntity<>(new Message("Error al crear el usuario: " +e, true, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Message> setStatus(@PathVariable("id") long id){
        return userService.setStatus(id);
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
