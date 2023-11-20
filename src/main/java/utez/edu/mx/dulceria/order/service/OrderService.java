package utez.edu.mx.dulceria.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.order.model.Order;
import utez.edu.mx.dulceria.order.model.OrderDTO;
import utez.edu.mx.dulceria.order.repository.OrderRepository;
import utez.edu.mx.dulceria.statusOrder.model.Status_order;
import utez.edu.mx.dulceria.visit.model.Visit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAllOrders() {
        return new ResponseEntity<>(new Message("OK", false, orderRepository.findAll()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findOrderById(long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            return new ResponseEntity<>(new Message("OK", false, orderOptional.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Message("Not found", true, null), HttpStatus.NOT_FOUND);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<Message> saveOrder(OrderDTO orderDTO) {
        try {
            Status_order managedStatusOrder = entityManager.find(Status_order.class, orderDTO.getStatus().getId());
            Visit managedVisit = entityManager.find(Visit.class, orderDTO.getVisit().getId());

            Order order = new Order();
            order.setDescription(orderDTO.getDescription());
            order.setObservaciones(orderDTO.getObservaciones());
            order.setStatus(managedStatusOrder);
            order.setVisit(managedVisit);
            order.setProductList(orderDTO.getProductList());

            Order savedOrder = orderRepository.saveAndFlush(order);
            return new ResponseEntity<>(new Message("OK", false, savedOrder), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new Message("Error", true, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<Message> updateOrder(long id, OrderDTO updatedOrderDTO) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.map(order -> {
            Status_order managedStatusOrder = entityManager.find(Status_order.class, updatedOrderDTO.getStatus().getId());
            Visit managedVisit = entityManager.find(Visit.class, updatedOrderDTO.getVisit().getId());


            order.setDescription(updatedOrderDTO.getDescription());
            order.setObservaciones(updatedOrderDTO.getObservaciones());
            order.setStatus(managedStatusOrder);
            order.setVisit(managedVisit);
            order.setProductList(updatedOrderDTO.getProductList());

            Order savedOrder = orderRepository.save(order);
            return new ResponseEntity<>(new Message("OK", false, savedOrder), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(new Message("Not found", true, null), HttpStatus.NOT_FOUND));
    }

    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<Message> deleteOrder(long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(value -> {
            orderRepository.deleteById(id);
            return new ResponseEntity<>(new Message("OK", false, null), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(new Message("Not found", true, null), HttpStatus.NOT_FOUND));
    }
}