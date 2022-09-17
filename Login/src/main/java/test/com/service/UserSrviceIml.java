package test.com.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	private UserRepository userrepo;

	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	public UserSrviceIml( UserRepository userrepo) {
		this.userrepo = userrepo;
	}
	
	
	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User (registrationDto.getFirstName(), registrationDto.getLastName(), registrationDto.getEmail(), 
				             passwordEncoder.encode(registrationDto.getPassword()) , Arrays.asList(new Role ("role_user")));
		
		return userrepo.save(user);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userrepo.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException ("Invalid username or password. ");
		}
		
		return new org.springframework.security.core.userdetails.User (user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection <? extends GrantedAuthority> mapRolesToAuthorities (Collection <Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority (role.getName())).collect(Collectors.toList());
		

}
}
