package utez.edu.mx.dulceria.person.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.person.repository.PersonRepository;

import java.sql.SQLException;
import java.util.Optional;
import utez.edu.mx.dulceria.person.model.Person;

@Service
@Transactional
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll(){
        return  new ResponseEntity<>(new Message("OK",false,personRepository.findAll()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(Long id){
        Optional person = personRepository.findById(id);
        if(person.isPresent()){
            return  new ResponseEntity<>(new Message("OK",false,person), HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(new Message("Not found",true,null), HttpStatus.NOT_FOUND);
        }
    }
    @Transactional(rollbackFor = {SQLException.class}) //
    public ResponseEntity<Message> save(Person person){
        try{
            Person savedPerson = personRepository.saveAndFlush(person);
            return  new ResponseEntity<>(new Message("OK",false,personRepository.save(person)), HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(new Message("Error",true,e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @Transactional(rollbackFor = {SQLException.class}) //
    public ResponseEntity<Message> update(Long id, Object object){
        Optional<Person> optionalPerson = personRepository.findById(id);
        if(optionalPerson.isPresent()){
            try{
                Person person = (Person) object;
                person.setId(id);
                return  new ResponseEntity<>(new Message("OK",false,personRepository.save(person)), HttpStatus.OK);
            }catch (Exception e){
                return  new ResponseEntity<>(new Message("Error",true,e.getMessage()), HttpStatus.BAD_REQUEST);
            }
        }else{
            return  new ResponseEntity<>(new Message("Not found",true,null), HttpStatus.NOT_FOUND);
        }
    }
    @Transactional(rollbackFor = {SQLException.class}) //
    public ResponseEntity<Message> delete(Long id){
        Optional person = personRepository.findById(id);
        if(person.isPresent()){
            try{
                personRepository.deleteById(id);
                return  new ResponseEntity<>(new Message("OK",false,null), HttpStatus.OK);
            }catch (Exception e){
                return  new ResponseEntity<>(new Message("Error",true,e.getMessage()), HttpStatus.BAD_REQUEST);
            }
        }else{
            return  new ResponseEntity<>(new Message("Not found",true,null), HttpStatus.NOT_FOUND);
        }
    }



}
