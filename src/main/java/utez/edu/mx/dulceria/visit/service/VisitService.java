package utez.edu.mx.dulceria.visit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.person.model.Person;
import utez.edu.mx.dulceria.rol.model.Rol;
import utez.edu.mx.dulceria.statusVisit.model.StatusVisitRepository;
import utez.edu.mx.dulceria.statusVisit.model.Status_visit;
import utez.edu.mx.dulceria.store.model.Store;
import utez.edu.mx.dulceria.store.repository.StoreRepository;
import utez.edu.mx.dulceria.visit.model.Visit;
import utez.edu.mx.dulceria.visit.repository.VisitRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VisitService  {

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StatusVisitRepository StatusvisitRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Visit> visits = visitRepository.findAll();
        return new ResponseEntity<>(new Message("OK", false, visits), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public  ResponseEntity<Message> findByStore(Long id){
        List<Visit> visits = visitRepository.findByStoreId(id);
        return new ResponseEntity<>(new Message("Ok", false, visits), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public  ResponseEntity<Message> findByStatusNot(Long deliver, Long status){
        List<Visit> visits = visitRepository.findByStoreDeliverIdAndStatusIdNot(deliver, status);
        return new ResponseEntity<>(new Message("Ok", false, visits), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public  ResponseEntity<Message> findByDeliver(Long id){
        List<Visit> visits = visitRepository.findByStoreDeliverId(id);
        return new ResponseEntity<>(new Message("Ok", false, visits), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(Long id) {
        Optional<Visit> visit = visitRepository.findById(id);
        return visit.map(value -> new ResponseEntity<>(new Message("OK", false, value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new Message("Not found", true, null), HttpStatus.NOT_FOUND));
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(Visit visit) {
        Status_visit status = getByStatusVisit(visit.getStatus().getId()).orElseThrow(() -> new RuntimeException("status not found"));
        Store store = getByStore(visit.getStore().getId()).orElseThrow(() -> new RuntimeException("status not found"));

        visit.setStatus(status);
        visit.setStore(store);
        Visit savedVisit = visitRepository.saveAndFlush(visit);
        return new ResponseEntity<>(new Message("Visit registered successfully", false, savedVisit), HttpStatus.OK);
    }

    /*@Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(Visit visit) {
        Optional<Visit> optionalVisit = visitRepository.findById(visit.getId());
        return optionalVisit.map(value -> {
            Visit updatedVisit = visitRepository.saveAndFlush(visit);
            return new ResponseEntity<>(new Message("Visit updated successfully", false, updatedVisit), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(new Message("Not found", true, null), HttpStatus.NOT_FOUND));
    }*/

    @Transactional(rollbackFor = {SQLException.class}) // si encuenra un error lo vuelve a hacer
    public ResponseEntity<Message> update(Visit visit){
        if(visitRepository.existsById(visit.getId())){
            Status_visit status = getByStatusVisit(visit.getStatus().getId()).orElseThrow(() -> new RuntimeException("status not found"));
            Store store = getByStore(visit.getStore().getId()).orElseThrow(() -> new RuntimeException("status not found"));

            visit.setStatus(status);
            visit.setStore(store);
            return new ResponseEntity<>(new Message("OK", false, visitRepository.saveAndFlush(visit)), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("La visita no está registrada", true, null), HttpStatus.BAD_REQUEST);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> delete(Long id) {
        Optional<Visit> optionalVisit = visitRepository.findById(id);
        return optionalVisit.map(value -> {
            try {
                visitRepository.deleteById(id);
                return new ResponseEntity<>(new Message("OK", false, null), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new Message("Error", true, e.getMessage()), HttpStatus.BAD_REQUEST);
            }
        }).orElseGet(() -> new ResponseEntity<>(new Message("Not found", true, null), HttpStatus.NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Optional<Status_visit> getByStatusVisit(long id){
        return StatusvisitRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Store> getByStore(long id){
        return storeRepository.findById(id);
    }
}
