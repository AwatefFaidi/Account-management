package org.sid.sec;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
   auth.inMemoryAuthentication().
   withUser("admin").password("1234").roles("ADMIN","USER");
   
   auth.inMemoryAuthentication().
   withUser("user").password("1234").roles("USER");
	}
	 protected void configure(HttpSecurity http) throws Exception 
	 {
		 http.formLogin().loginPage("/login");
		 //http.authorizeRequest
		 http.authorizeRequests().antMatchers("/operation","/consulterCompte").hasRole("USER");
		 http.authorizeRequests().antMatchers("/saveOperation").hasRole("ADMIN");
	     http.exceptionHandling().accessDeniedPage("/403");
		 //super.configure(http);
	 }
}
