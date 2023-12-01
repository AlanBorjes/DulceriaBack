package utez.edu.mx.dulceria;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utez.edu.mx.dulceria.rol.model.Rol;
import utez.edu.mx.dulceria.rol.repository.RolRepository;

@SpringBootApplication
public class DulceriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DulceriaApplication.class, args);
	}

}
