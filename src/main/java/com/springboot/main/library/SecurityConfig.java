package com.springboot.main.library;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.springboot.main.library.service.UserService;

@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Bean
	public Logger getLogger() {
		return LoggerFactory.getLogger("Log Records");
	}
	

	@Autowired
	private UserService userService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
		System.out.println("configure...called");
		auth.authenticationProvider(getProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		
		http
		.authorizeRequests()
		.antMatchers("/Book/add/{id}","/Book/update/{id}","/auth/login","/Book/delete/{bid}","/customer/post",
				"/admin/delete/{id}","/admin/update/{id}","/category/getall","/customer/getallcategory","/admin/add",
				"/Book/all/{id}","/Book/all","/customer/getone/{bookTile}","/customer/getall","/customerBook/getall","/customerBook/customerid/{cid}",
				"/Book/update/{bid}/{id}","/customerBook/{cid}/{bid}","/customer/getbycategoryid","/user/login",
				"/Book/getbybook/{bid}","/Book/getwithauthdesc","/Book/getwithbookdesc","customerBook/bookid/{bid}","/customerBook/create/{customerId}").permitAll()
		.antMatchers(HttpMethod.POST,"/user/login").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and()
		.csrf().disable()
		.cors();
	}
	
	@Bean
	public PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public DaoAuthenticationProvider getProvider() {
		System.out.println("getprovider...called");
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(getEncoder());
		dao.setUserDetailsService(userService);
		
		return dao;
	}
	
	
	
}