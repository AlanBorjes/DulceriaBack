package utez.edu.mx.dulceria.deliveryPerson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.deliveryPerson.repository.DeliveryPersonRepository;
import java.util.Optional;
import utez.edu.mx.dulceria.deliveryPerson.model.DeliveryPerson;

@Service
@Transactional
public class DeliveryPersonService {

    @Autowired
    DeliveryPersonRepository deliveryPersonRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        return new ResponseEntity<>(new Message("OK", false, deliveryPersonRepository.findAll()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(Long id) {
        Optional<DeliveryPerson> deliveryPerson = deliveryPersonRepository.findById(id);
        if (deliveryPerson.isPresent()) {
            return new ResponseEntity<>(new Message("OK", false, deliveryPerson), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Message("Not found", true, null), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Message> save(Object object) {
        try {
            DeliveryPerson deliveryPerson = (DeliveryPerson) object;
            return new ResponseEntity<>(new Message("OK", false, deliveryPersonRepository.save(deliveryPerson)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message("Error", true, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Message> update(Long id, Object object) {
        Optional<DeliveryPerson> optionalDeliveryPerson = deliveryPersonRepository.findById(id);
        if (optionalDeliveryPerson.isPresent()) {
            try {
                DeliveryPerson deliveryPerson = (DeliveryPerson) object;
                deliveryPerson.setId(id);
                return new ResponseEntity<>(new Message("OK", false, deliveryPersonRepository.save(deliveryPerson)), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new Message("Error", true, e.getMessage()), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new Message("Not found", true, null), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Message> delete(Long id) {
        Optional<DeliveryPerson> deliveryPerson = deliveryPersonRepository.findById(id);
        if (deliveryPerson.isPresent()) {
            try {
                deliveryPersonRepository.deleteById(id);
                return new ResponseEntity<>(new Message("OK", false, null), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new Message("Error", true, e.getMessage()), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new Message("Not found", true, null), HttpStatus.NOT_FOUND);
        }
    }
}

