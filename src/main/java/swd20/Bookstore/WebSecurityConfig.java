package swd20.Bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import swd20.Bookstore.web.UserDetailServiceImpl;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
    private UserDetailServiceImpl userDetailsService;	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { //configure which endpoints need authentication and which ones don't
		 http
	        .authorizeRequests().antMatchers("/css/**").permitAll() // Enable css when logged out. .permitAll means that everyone can access it without logging in
	        .and()
	        .authorizeRequests().anyRequest().authenticated()
	        .and()
	      .formLogin()
	          .defaultSuccessUrl("/booklist", true)
	          .permitAll()
	          .and()
	      .logout()
	          .permitAll();
	}
	
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	
    	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        
    }

}
