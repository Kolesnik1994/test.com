package spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.com.LoginApplication;
import test.com.model.User;
import test.com.repository.UserRepository;

/**
 * Test class for Repository 
 * @author VLadislav K
 */
 

@SpringBootTest (classes = LoginApplication.class)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepo;
	
	@Test
	public void saveUserTest() {
		
		User user = new User();
		user.setFirstName("Sasha");
		user.setLastName("Meison");
		user.setEmail("shas@gmail.com");
		user.setPassword("123");
		
		userRepo.save(user);
		
		Assertions.assertNotNull(user);
	}	

}

