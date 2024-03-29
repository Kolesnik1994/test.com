package test.com.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import test.com.service.UserService;
import test.com.web.dto.UserRegistrationDto;

/**
 * Controller class for registration form
 * @author VLadislav K
 */
@Controller
@RequestMapping ("/registration")
public class UserRegistrationConroller {
	
	private UserService userService;

	public UserRegistrationConroller(UserService userservice) {
		this.userService = userservice;
	}
	
	@ModelAttribute ("user")
	public UserRegistrationDto registrationDto () {
		return new UserRegistrationDto();
		
	}
	
	@GetMapping 
	public String showRegistrationForm (Model model) {
		model.addAttribute("user", new UserRegistrationDto());
		return "registration";
	}
		
	@PostMapping 
	public String registerUserAccount (@ModelAttribute ("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	
	}
}
