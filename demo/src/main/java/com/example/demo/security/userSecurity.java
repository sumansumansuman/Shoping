package com.example.demo.security;

/*

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.service.*;



@Configuration
@EnableWebSecurity
public class userSecurity extends WebSecurityConfigurerAdapter {
	

   @Autowired
   userService userService;
   
   @Autowired
   oAuthService oid; 
   
 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/* http.authorizeRequests()
		  .antMatchers("/resources/static/register","/user/register", "/static/**", "/css/**", "/js/**", "/img/**", "/icon/**").permitAll()
		  .anyRequest().authenticated()
		  .and()
		  .formLogin()
		  .loginPage("/user/login")
		  .defaultSuccessUrl("/",true)
		  .permitAll()
		  .and()
		  .logout();
		  
	 
		http
        .antMatcher("/**").authorizeRequests()
        .antMatchers("/resources/static/register","/user/login","/user/register", "/static/**", "/css/**", "/js/**", "/img/**", "/icon/**").permitAll()	
        .anyRequest().authenticated()
        .and()
        .oauth2Login().userInfoEndpoint().oidcUserService(oid)
        .and(). loginPage("/user/login").defaultSuccessUrl("/",true);
      
	}
	
	
	@Bean
	 public PasswordEncoder getPasswordEncoder() {
	       return NoOpPasswordEncoder.getInstance();
	 }

}
*/
