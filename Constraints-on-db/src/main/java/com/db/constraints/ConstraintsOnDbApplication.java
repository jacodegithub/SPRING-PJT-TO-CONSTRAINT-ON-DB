package com.db.constraints;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@RestController
@Transactional
public class ConstraintsOnDbApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ConstraintsOnDbApplication.class, args);
		
	}

	
	
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("/create-user")
	public User createUser(@RequestBody User user) {
		ObjectMapper om = new ObjectMapper();
		String jsonUsername = null;
		String jsonPassword = null;
		try {
			jsonUsername = om.writeValueAsString(user.getUsername());
			jsonPassword = om.writeValueAsString(user.getPassword());
			System.out.println(jsonUsername +" "+ jsonPassword);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// grant permission
		
//		userRepo.grantCreateUserPermission(jsonUsername);
		userRepo.createSpecialUserAndGrantPermission(jsonUsername, jsonPassword);
		User savedUser = userRepo.save(user);
		return savedUser;
	}
}
