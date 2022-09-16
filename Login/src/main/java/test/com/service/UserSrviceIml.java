package test.com.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import test.com.model.Role;
import test.com.model.User;
import test.com.repository.UserRepository;
import test.com.web.dto.UserRegistrationDto;


/**
 * Implementation of {@link test.com.service.UserSevice} interface
 * 
 * @author VLadislav K
 */
@Service
@Component
public class UserSrviceIml implements UserService {

	@Autowired
	private UserRepository userrepo;
	public UserSrviceIml(UserRepository userrepo) {
		this.userrepo = userrepo;
	}


	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User (registrationDto.getFirstName(), registrationDto.getLastName(), registrationDto.getEmail(), 
				              registrationDto.getPassword(), Arrays.asList(new Role ("role_user")));
		
		return userrepo.save(user);
	}

}
