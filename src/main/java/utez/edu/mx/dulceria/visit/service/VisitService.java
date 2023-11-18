package utez.edu.mx.dulceria.visit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.rol.model.Rol;
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

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Visit> visits = visitRepository.findAll();
        return new ResponseEntity<>(new Message("OK", false, visits), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(Long id) {
        Optional<Visit> visit = visitRepository.findById(id);
        return visit.map(value -> new ResponseEntity<>(new Message("OK", false, value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new Message("Not found", true, null), HttpStatus.NOT_FOUND));
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(Visit visit) {
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
}
