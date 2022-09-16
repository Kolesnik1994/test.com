package test.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
 * the main class, that start application 
 * @author VLadislav K 
 */
@SpringBootApplication
@EntityScan ("test.com.model")  
@ComponentScan ("test.com")    
@EnableJpaRepositories ("test.com.repository")  
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

}
