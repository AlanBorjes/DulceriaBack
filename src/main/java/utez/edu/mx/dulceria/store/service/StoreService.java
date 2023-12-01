package utez.edu.mx.dulceria.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.person.model.Person;
import utez.edu.mx.dulceria.person.repository.PersonRepository;
import utez.edu.mx.dulceria.store.repository.StoreRepository;
import utez.edu.mx.dulceria.store.model.Store;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private PersonRepository personRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        //List<Store> stores = storeRepository.findAll();
        return new ResponseEntity<>(new Message("OK", false, storeRepository.findAll()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(Long id) {
        Optional<Store> store = storeRepository.findById(id);
        return store.map(value -> new ResponseEntity<>(new Message("OK", false, value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new Message("Not found", true, null), HttpStatus.NOT_FOUND));
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(Store store) {
        Person owner = getByPerson(store.getOwner().getId()).orElseThrow(() -> new RuntimeException("Owner not found"));
        Person deliver = getByPerson(store.getDeliver().getId()).orElseThrow(() -> new RuntimeException("Deliver not found"));

        store.setOwner(owner);
        store.setDeliver(deliver);

        Store savedStore = storeRepository.saveAndFlush(store);
        return new ResponseEntity<>(new Message("Store registered successfully", false, savedStore), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(Store store) {
        Optional<Store> optionalStore = storeRepository.findById(store.getId());

        return optionalStore.map(value -> {
            Store updatedStore = storeRepository.saveAndFlush(store);
            return new ResponseEntity<>(new Message("Store updated successfully", false, updatedStore), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(new Message("Not found", true, null), HttpStatus.NOT_FOUND));
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> delete(Long id) {
        Optional<Store> optionalStore = storeRepository.findById(id);
        return optionalStore.map(value -> {
            try {
                storeRepository.deleteById(id);
                return new ResponseEntity<>(new Message("OK", false, null), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new Message("Error", true, e.getMessage()), HttpStatus.BAD_REQUEST);
            }
        }).orElseGet(() -> new ResponseEntity<>(new Message("Not found", true, null), HttpStatus.NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Optional<Person> getByPerson(long id){
        return personRepository.findById(id);
    }
}
