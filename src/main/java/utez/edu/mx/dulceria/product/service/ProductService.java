package utez.edu.mx.dulceria.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.product.model.Product;
import utez.edu.mx.dulceria.product.repository.ProductRepository;

import java.sql.SQLException;
import java.util.Optional;


@Service
@Transactional
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll(){
        return new ResponseEntity<>(new Message("OK", false, productRepository.findAll()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(long id){
        if (productRepository.existsById(id)){
            return new ResponseEntity<>(new Message("OK", false, productRepository.findById(id)), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("No existe el producto", true, productRepository.findById(id)), HttpStatus.BAD_REQUEST);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(Product product){
        Optional<Product> existsProduct = productRepository.findById(product.getId());
        Product savedProduct = productRepository.saveAndFlush(product);
        return new ResponseEntity<>(new Message("Producto registrado correctamente", false, savedProduct), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(Product product){
        if (productRepository.existsById(product.getId())){
            return new ResponseEntity<>(new Message("OK", false, productRepository.saveAndFlush(product)), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("El producto no existe", true, null), HttpStatus.BAD_REQUEST);
    }
}
