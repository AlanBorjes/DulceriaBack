package utez.edu.mx.dulceria.user.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.person.model.Person;
import utez.edu.mx.dulceria.person.repository.PersonRepository;
import utez.edu.mx.dulceria.rol.model.Rol;
import utez.edu.mx.dulceria.rol.repository.RolRepository;
import utez.edu.mx.dulceria.user.DTO.UserDTO;
import utez.edu.mx.dulceria.user.model.User;
import utez.edu.mx.dulceria.user.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PersonRepository personRepository;

    @Autowired
    RolRepository rolRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll(){
        return  new ResponseEntity<>(new Message("OK",false,userRepository.findAll()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(long id){
        if( userRepository.existsById(id)){
            return new ResponseEntity<>(new Message("OK", false, userRepository.findById(id)), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("El usuario ya existe", true, userRepository.findById(id)), HttpStatus.BAD_REQUEST);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAllRepatidor(Rol role){
        List<User> users = userRepository.findUsersByAuthoritiesAndStatus(role, 1);

        return  new ResponseEntity<>(new Message("OK",false,users), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> setStatus(long id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            if (user.getStatus() == 1) {
                user.setStatus(0);
            } else {
                user.setStatus(1);
            }
            return new ResponseEntity<>(new Message("OK", false, userRepository.saveAndFlush(user)), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new Message("El usuario no existe", true, null), HttpStatus.BAD_REQUEST);

        }
    }


    @Transactional(rollbackFor = {SQLException.class}) // si encuenra un error lo vuelve a hacer
    public ResponseEntity<Message> save(User user){

            System.out.println(user.getUsername());

            if (userRepository.existsByUsername(user.getUsername())) {
                return new ResponseEntity<>(new Message("El usuario ya existe", true, null), HttpStatus.BAD_REQUEST);
            }
            if(userRepository.existsByPersonEmail(user.getPerson().getEmail())) {
                return new ResponseEntity<>(new Message("La persona ya cuenta con un usuario", true, null), HttpStatus.BAD_REQUEST);
            }
            Person personTemp = personRepository.getById(user.getPerson().getId());
            user.setPerson(personTemp);
            Hibernate.initialize(user.getPerson());
            user.setUsername(personTemp.getEmail());
            User  e =  userRepository.saveAndFlush(user);
            UserDTO use = new UserDTO(user.getId(), user.getPassword(),user.getCode(),user.getCode(),user.getAuthorities(),user.getStatus(),user.getUsername());
            return new ResponseEntity<>(new Message("OK", false, use), HttpStatus.OK);

    }

    @Transactional(rollbackFor = {SQLException.class}) // si encuenra un error lo vuelve a hacer
    public ResponseEntity<Message> save2(User user,int rol){
        Set<Rol> roles = null;
        if(rol ==1 ){
           Rol rol1 = getByIdRol(1);
           roles.add(rol1);
        }else if (rol ==2 ){
            Rol rol2 = getByIdRol(1);
            roles.add(rol2);
        }else{
            Rol rol1 = getByIdRol(1);
            Rol rol2 = getByIdRol(1);
            roles.add(rol1);
            roles.add(rol2);
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>(new Message("El usuario ya existe", true, null), HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByPersonEmail(user.getPerson().getEmail())) {
            return new ResponseEntity<>(new Message("La persona ya cuenta con un usuario", true, null), HttpStatus.BAD_REQUEST);
        }
        Person personTemp = personRepository.getById(user.getPerson().getId());
        user.setPerson(personTemp);
        Hibernate.initialize(user.getPerson());
        user.setUsername(personTemp.getEmail());
        user.setAuthorities(roles);
        User  e =  userRepository.saveAndFlush(user);
        UserDTO use = new UserDTO(user.getId(), user.getPassword(),user.getCode(),user.getCode(),user.getAuthorities(),user.getStatus(),user.getUsername());
        return new ResponseEntity<>(new Message("OK", false, use), HttpStatus.OK);

    }
    @Transactional(readOnly = true)
    public Optional<User> getByCode(String code){
        return userRepository.findByCode(code);
    }


    @Transactional(readOnly = true)
    public Optional<User> getById(long id){
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Rol getByIdRol(long id){
        return rolRepository.findById(id);
    }

}

