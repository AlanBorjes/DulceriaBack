package utez.edu.mx.dulceria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import utez.edu.mx.dulceria.person.model.Person;
import utez.edu.mx.dulceria.person.repository.PersonRepository;
import utez.edu.mx.dulceria.rol.model.Rol;
import utez.edu.mx.dulceria.rol.repository.RolRepository;
import utez.edu.mx.dulceria.statusOrder.model.StatusOrderRepository;
import utez.edu.mx.dulceria.statusOrder.model.Status_order;
import utez.edu.mx.dulceria.statusVisit.model.StatusVisitRepository;
import utez.edu.mx.dulceria.statusVisit.model.Status_visit;
import utez.edu.mx.dulceria.user.model.User;
import utez.edu.mx.dulceria.user.service.UserService;

import java.util.Set;

@Component
@Service
public class MyRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private  RolRepository repositoryrol;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    StatusVisitRepository statusVisitRepository;

    @Autowired
    StatusOrderRepository statusOrderRepository;

    Rol rol = new Rol("Administrador", "Administrador del sistema");
    Rol rol1 = new Rol("Repatidor", "Repartidor de la dulceria");
    Status_visit status_visit = new Status_visit("Realizada");
    Status_visit status_visit1 = new Status_visit("Pendiente");
    Status_order status_order = new Status_order("Realizada");
    Status_order status_order1 = new Status_order("Pendiente");

    Person person = new Person("Admin","","","","admin",0,"M");
    Person person2 = new Person("Jesus Perez","Brito salgado","3 de mayo N.4","7773195078","jesus@gmail.com",23,"M");
    @Override
    public void run(String... args) throws Exception {
        rol = repositoryrol.saveAndFlush(rol);
        rol1 = repositoryrol.saveAndFlush(rol1);
        Set<Rol> roles = Set.of(rol,rol1);
        logger.info("Roles insertados");
        person = personRepository.saveAndFlush(person);
        person2 = personRepository.saveAndFlush(person2);
        logger.info("Personas insertadas");
        userService.save(new User(passwordEncoder.encode("root"),"admin",person,1,roles ));
        logger.info("Usuario insertado");
        status_visit = statusVisitRepository.saveAndFlush(status_visit);
        status_visit1 = statusVisitRepository.saveAndFlush(status_visit1);
        logger.info("Status Visit insertados");
        status_order = statusOrderRepository.saveAndFlush(status_order);
        status_order1 = statusOrderRepository.saveAndFlush(status_order1);
        logger.info("Status Order insertados");
    }
}
