package com.springboot.main.library.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.library.model.Admin;
import com.springboot.main.library.model.Customer;
import com.springboot.main.library.model.User;
import com.springboot.main.library.service.AdminService;
import com.springboot.main.library.service.CustomerService;
import com.springboot.main.library.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
	
    @Autowired
    private UserService userService;
   

    
    @PostMapping("/auth/login")
    public User login(Principal principal) {
        String username = principal.getName();
        User user = (User) userService.loadUserByUsername(username);
        return user;
    }
    
    
}
