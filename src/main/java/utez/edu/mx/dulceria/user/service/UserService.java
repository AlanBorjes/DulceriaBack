package utez.edu.mx.dulceria.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.user.repository.UserRepository;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll(){
        return  new ResponseEntity<>(new Message("OK",false,userRepository.findAll()), HttpStatus.OK);
    }
}
