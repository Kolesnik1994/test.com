package test.com.service;

import org.springframework.security.core.userdetails.UserDetailsService;


import test.com.model.User;
import test.com.web.dto.UserRegistrationDto;

/*
 * Interface used for store data 
 * @author VLadislav K 
 */

public interface UserService extends UserDetailsService {
	
	User save (UserRegistrationDto registrationDto);
	
	

}
