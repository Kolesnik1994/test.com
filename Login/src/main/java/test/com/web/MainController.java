package test.com.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Main Controller class
 * 
 * @author VLadislav K
 */
@Controller
public class MainController {
	
	@GetMapping ("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping ("/")
	public String home () {
		return "index";
	
	}

}
