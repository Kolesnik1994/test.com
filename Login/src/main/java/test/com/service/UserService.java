package test.com.service;

import org.springframework.stereotype.Service;

import test.com.model.User;
import test.com.web.dto.UserRegistrationDto;

/*
 * Service class for {@link test.com.model.User}
 * 
 * @author VLadislav K
 * 
 */
@Service
public interface UserService {
	
	User save (UserRegistrationDto registrationDto);

}
