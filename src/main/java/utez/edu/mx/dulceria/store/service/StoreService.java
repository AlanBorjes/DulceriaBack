package utez.edu.mx.dulceria.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.store.repository.StoreRepository;
import utez.edu.mx.dulceria.store.model.Store;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class StoreService {
    @Autowired
    StoreRepository storeRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll(){
        return  new ResponseEntity<>(new Message("OK",false,storeRepository.findAll()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(Long id){
        Optional store = storeRepository.findById(id);
        if(store.isPresent()){
            return  new ResponseEntity<>(new Message("OK",false,store), HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(new Message("Not found",true,null), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Message> save(Object object){
        try{
            Store store = (Store) object;
            return  new ResponseEntity<>(new Message("OK",false,storeRepository.save(store)), HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(new Message("Error",true,e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Message> update(Long id, Object object){
        Optional<Store> optionalStore = storeRepository.findById(id);
        if(optionalStore.isPresent()){
            try{
                Store store = (Store) object;
                store.setId(id);
                return  new ResponseEntity<>(new Message("OK",false,storeRepository.save(store)), HttpStatus.OK);
            }catch (Exception e){
                return  new ResponseEntity<>(new Message("Error",true,e.getMessage()), HttpStatus.BAD_REQUEST);
            }
        }else{
            return  new ResponseEntity<>(new Message("Not found",true,null), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Message> delete(Long id){
        Optional<Store> optionalStore = storeRepository.findById(id);
        if(optionalStore.isPresent()){
            try{
                storeRepository.deleteById(id);
                return  new ResponseEntity<>(new Message("OK",false,null), HttpStatus.OK);
            }catch (Exception e){
                return  new ResponseEntity<>(new Message("Error",true,e.getMessage()), HttpStatus.BAD_REQUEST);
            }
        }else{
            return  new ResponseEntity<>(new Message("Not found",true,null), HttpStatus.NOT_FOUND);
        }
    }

}
