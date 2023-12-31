package utez.edu.mx.dulceria.person.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.person.repository.PersonRepository;
import utez.edu.mx.dulceria.person.model.Person;
import utez.edu.mx.dulceria.person.model.PersonDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<PersonDTO> personDTOList = personRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(new Message("OK", false, personDTOList), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.map(value -> new ResponseEntity<>(new Message("OK", false, convertToDTO(value)), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new Message("Not found", true, null), HttpStatus.NOT_FOUND));
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(PersonDTO personDTO) {
        try {
            Person person = convertToEntity(personDTO);
            Person savedPerson = personRepository.saveAndFlush(person);
            return new ResponseEntity<>(new Message("OK", false, convertToDTO(savedPerson)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message("Error", true, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(Long id, PersonDTO updatedPersonDTO) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        return optionalPerson.map(person -> {
            Person updatedPerson = convertToEntity(updatedPersonDTO);
            updatedPerson.setId(id);
            return new ResponseEntity<>(new Message("OK", false, convertToDTO(personRepository.save(updatedPerson))), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(new Message("Not found", true, null), HttpStatus.NOT_FOUND));
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> delete(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.map(value -> {
            personRepository.deleteById(id);
            return new ResponseEntity<>(new Message("OK", false, null), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(new Message("Not found", true, null), HttpStatus.NOT_FOUND));
    }

    private PersonDTO convertToDTO(Person person) {
        return new PersonDTO(
                person.getId(),
                person.getName(),
                person.getLastName(),
                person.getAddress(),
                person.getPhone(),
                person.getEmail(),
                person.getEdad(),
                person.getSexo()
        );
    }

    private Person convertToEntity(PersonDTO personDTO) {
        return new Person(
                personDTO.getId(),
                personDTO.getName(),
                personDTO.getLastName(),
                personDTO.getAddress(),
                personDTO.getPhone(),
                personDTO.getEmail(),
                personDTO.getEdad(),
                personDTO.getSexo()
        );
    }
}
