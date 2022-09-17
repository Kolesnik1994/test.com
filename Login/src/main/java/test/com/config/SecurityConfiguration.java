package test.com.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import test.com.service.UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private UserService userService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder (passwordEncoder());
		return auth;
		
	}
	
	 @Bean
	 @Primary
     public AuthenticationManagerBuilder authenticationManager(AuthenticationManagerBuilder auth ) throws Exception {
		 return auth.authenticationProvider(authenticationProvider());
	 }
	
	//protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	//auth.authenticationProvider(authenticationProvider());
	//}

	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/registration**", 
				                             "/js/**",
			                                 "/css/**",
				                             "/img/**").
		                                                 permitAll().
		                                                 anyRequest().
		                                                 authenticated().
		                                                 and().
		                                                 formLogin().
		                                                 loginPage("/login").
		                                                 permitAll().
		                                                 and().
		                                                 logout().
		                                                 invalidateHttpSession(true).
		                                                 clearAuthentication(true).
		                                                 logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
		                                                 logoutSuccessUrl ("/login?logout").
		                                                 permitAll();
		return http.build();
		                                                 

}
}