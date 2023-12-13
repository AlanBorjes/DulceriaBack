package utez.edu.mx.dulceria.orderHasProduct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.orderHasProduct.model.Order_has_Product;
import utez.edu.mx.dulceria.orderHasProduct.repository.OrderHasProductRepository;
import utez.edu.mx.dulceria.rol.model.Rol;
import utez.edu.mx.dulceria.store.model.Store;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class OrderHasProductService {

    @Autowired
    private OrderHasProductRepository orderHasProductRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAllorderHasProduct() {
        return new ResponseEntity<>(new Message("OK", false, orderHasProductRepository.findAll()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(long id){
        if(orderHasProductRepository.existsById(id)){
            return new ResponseEntity<>(new Message("OK", false, orderHasProductRepository.findById(id)), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("El Rol no existe", true, orderHasProductRepository.findById(id)), HttpStatus.BAD_REQUEST);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(Order_has_Product orderProduct) {
        Order_has_Product existingProduct = orderHasProductRepository
                .findByOrderAndProduct(orderProduct.getOrder(), orderProduct.getProduct());
        if (existingProduct != null) {
            existingProduct.setCantidad(existingProduct.getCantidad() + orderProduct.getCantidad());
            orderHasProductRepository.saveAndFlush(existingProduct);
            return new ResponseEntity<>(new Message("Cantidad del producto actualizada correctamente", false, existingProduct), HttpStatus.OK);
        } else {
            Order_has_Product saved = orderHasProductRepository.saveAndFlush(orderProduct);
            return new ResponseEntity<>(new Message("Producto registrado correctamente en la orden", false, saved), HttpStatus.OK);
        }
    }

    @Transactional(rollbackFor = {SQLException.class}) // si encuenra un error lo vuelve a hacer
    public ResponseEntity<Message> update(Order_has_Product rol){
        if(orderHasProductRepository.existsById(rol.getId())){
            return new ResponseEntity<>(new Message("OK", false, orderHasProductRepository.saveAndFlush(rol)), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("El Project no existe", true, null), HttpStatus.BAD_REQUEST);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> delete(Long id) {
        Optional<Order_has_Product> optionalStore = orderHasProductRepository.findById(id);
        return optionalStore.map(value -> {
            try {
                orderHasProductRepository.deleteById(id);
                return new ResponseEntity<>(new Message("OK", false, null), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new Message("Error", true, e.getMessage()), HttpStatus.BAD_REQUEST);
            }
        }).orElseGet(() -> new ResponseEntity<>(new Message("Not found", true, null), HttpStatus.NOT_FOUND));
    }
}
