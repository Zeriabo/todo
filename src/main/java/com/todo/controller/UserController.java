package com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.todo.api.AuthResponse;
import com.todo.model.User;
import com.todo.service.UserService;
import com.todo.util.JwtTokenUtil;
import java.lang.reflect.InvocationTargetException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import java.util.Date;  

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class UserController {

	
	
	@Autowired
	UserService userservice;

	
	 @Autowired 
	 JwtTokenUtil jwtUtil;
	public UserController() {
		
		
	}
	
    
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/signup", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })

	public ResponseEntity signUp(@RequestBody User user) throws Exception {
 try {
	 User registered= userservice.register(user);
	 if(registered==null)
     {
    	 return ResponseEntity.status(500).body("Error: not registered!");
     }
	 
	 return  ResponseEntity.ok().body(registered);
 }catch(Exception ex)
 {
	 return  ResponseEntity.status(500).body(ex.getMessage());
 }
	
    
	
	}
	
	@PostMapping(value = "/signin", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@SuppressWarnings("rawtypes")
	public ResponseEntity signIn(@RequestBody User user) throws Exception {
	
		User loggedUser = userservice.signIn(user.getEmail(), user.getPassword());
		if(loggedUser !=  null)
		{
			String accessToken = jwtUtil.generateAccessToken(loggedUser);
			AuthResponse response = new AuthResponse(user.getEmail(), accessToken);
			
			 return ResponseEntity.ok().body(response);
		}
		return  ResponseEntity.notFound().build();
	
	
		

	}
	
	@PostMapping(value = "/checkToken", consumes = { MediaType.ALL_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@SuppressWarnings("rawtypes")
	public ResponseEntity checkToken(@RequestParam String email,@RequestParam String token) throws Exception {
	
	      try {
	    	  Jws<Claims> checked = JwtTokenUtil.parseJwt(token);
	    	  System.out.print(checked);
	      }catch(Exception ex)
	      {
	    	  if(ex instanceof InvocationTargetException)
	    	  {
	    		  return  ResponseEntity.status(720).body("The Access Token is not correct");
	    	  }
	    	  return  ResponseEntity.status(500).body(ex);
	      }
			
		
		return  ResponseEntity.ok(true);
	}
	
	@PutMapping(value = "/updatepassword", consumes = { MediaType.ALL_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@SuppressWarnings("rawtypes")
	public ResponseEntity changePassword(@RequestParam String email,@RequestParam String token,@RequestParam String password) throws Exception {
		Date date = new Date();  
		boolean found = false;
	      try {
	    	  Jws<Claims> checked = JwtTokenUtil.parseJwt(token);
	    	 
              if(checked.getBody().getExpiration().after(date))
              {
            	  String subject = checked.getBody().getSubject();
            	  
            	 found = subject.contains(email);
                  } 
            	  
            	  if(found)
            	  {
            		 int updated= userservice.changePassword(email, password);
            		  return  ResponseEntity.status(200).body(updated>-1);
            	  }
            	 
              
	      }catch(Exception ex)
	      {
	    	  if(ex instanceof InvocationTargetException)
	    	  {
	    		  return  ResponseEntity.status(720).body("The Access Token is not correct");
	    	  }
	    	  return  ResponseEntity.status(500).body(ex);
	      }
			
		
		return  ResponseEntity.ok(true);
	}
	
}
