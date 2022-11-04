package com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.todo.model.User;
import com.todo.repository.UserRepository;
import com.todo.service.UserService;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class UserController {

	
	
	@Autowired
	UserService userservice;
	
	public UserController() {
		
		
	}
	
//    @GetMapping
//    public ResponseEntity getUsers()
//    {
//    	return ResponseEntity.ok(this.userRepository.findAll());
//    }
    
    
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/signup", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })

	public ResponseEntity signUp(@RequestBody User user) throws Exception {
		
	User registered= userservice.register(user);
     if(registered==null)
     {
    	 return ResponseEntity.status(500).body("Error: not registered!");
     }
	return  ResponseEntity.ok().body(registered);
	}
//	
//	@PostMapping(value = "/signin", consumes = { MediaType.APPLICATION_JSON_VALUE,
//			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
//					MediaType.APPLICATION_XML_VALUE })
	
	
//	@SuppressWarnings("rawtypes")
//	public ResponseEntity singIn(@RequestBody User user) throws Exception {
//		
//		return ResponseEntity.ok().body(userRepository.save(user));
//
//	}
}
