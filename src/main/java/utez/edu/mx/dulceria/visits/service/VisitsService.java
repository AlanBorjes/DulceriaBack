package utez.edu.mx.dulceria.visits.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.visits.model.Visits;
import utez.edu.mx.dulceria.visits.repository.VisitsRepository;

import java.util.Optional;

@Service
@Transactional
public class VisitsService {
    @Autowired
    VisitsRepository visitsRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll(){
        return  new ResponseEntity<>(new Message("OK",false,visitsRepository.findAll()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(Long id){
        Optional store = visitsRepository.findById(id);
        if(store.isPresent()){
            return  new ResponseEntity<>(new Message("OK",false,store), HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(new Message("Not found",true,null), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Message> save(Object object){
        try{
            Visits store = (Visits) object;
            return  new ResponseEntity<>(new Message("OK",false,visitsRepository.save(store)), HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(new Message("Error",true,e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Message> update(Long id, Object object){
        Optional<Visits> optionalStore = visitsRepository.findById(id);
        if(optionalStore.isPresent()){
            try{
                Visits store = (Visits) object;
                store.setId(id);
                return  new ResponseEntity<>(new Message("OK",false,visitsRepository.save(store)), HttpStatus.OK);
            }catch (Exception e){
                return  new ResponseEntity<>(new Message("Error",true,e.getMessage()), HttpStatus.BAD_REQUEST);
            }
        }else{
            return  new ResponseEntity<>(new Message("Not found",true,null), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Message> delete(Long id){
        Optional<Visits> optionalStore = visitsRepository.findById(id);
        if(optionalStore.isPresent()){
            try{
                visitsRepository.deleteById(id);
                return  new ResponseEntity<>(new Message("OK",false,null), HttpStatus.OK);
            }catch (Exception e){
                return  new ResponseEntity<>(new Message("Error",true,e.getMessage()), HttpStatus.BAD_REQUEST);
            }
        }else{
            return  new ResponseEntity<>(new Message("Not found",true,null), HttpStatus.NOT_FOUND);
        }
    }

}
