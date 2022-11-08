package com.todo.service.impl;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import com.todo.exception.CredentialsException;
import com.todo.exception.NullException;
import com.todo.exception.UserAlreadyExistsException;
import com.todo.exception.UserNotFoundException;
import com.todo.model.User;
import com.todo.repository.UserRepository;
import com.todo.service.UserService;
import com.todo.serviceImpl.UserServiceImpl;

@RunWith(SpringRunner.class)
public class UserServiceImplIntegrationtest {


	 @TestConfiguration
	    static class UserServiceImplTestContextConfiguration {
		 @Bean
	        public UserService userService() {
			 
	            return new UserServiceImpl();
	        }
	    }
	 
	    @Autowired
	    private  UserService userService;
	    
	    @MockBean
	    private UserRepository userRepository;
	    
	   
	    
	    @Test
	    public void signInUser() throws Exception {
	    	
          User user = new User();
          user.setEmail("test@test.com");
          user.setPassword("test");
         
          Exception exception = assertThrows(NullException.class, () -> {
        	  userService.signIn(null, null);
          });
          
          String actualMessage = exception.getMessage();
          
          assertTrue(actualMessage.contains("The entered data should not be null!"));
          
         
	     }
	    @Test
	    public void signInUserCredentialsWrong() throws Exception {
	    	
          User user = new User();
          user.setEmail("test@test.com");
          user.setPassword("test");
         
          Exception exception2 = assertThrows(CredentialsException.class, () -> {
          	  userService.signIn("asdlkjaslkdjas","jakshdkjashdjk");
            });
            
            String actualMessage2 = exception2.getMessage();
            
            assertTrue(actualMessage2.contains("The user or password wrong!"));
            
            
	     }
	 
	    @Test
	    public void changePassword() throws Exception {
	    	
          User user = new User();
          user.setEmail("test@test.com");
          user.setPassword("test");
    
          userService.register(user);
          
         
          Exception exception = assertThrows(UserNotFoundException.class, () -> {
        	  userService.changePassword(null, null);
          });
          
          String actualMessage = exception.getMessage();
          
          assertTrue(actualMessage.contains("The user is not found!"));
          
          Exception exception2 = assertThrows(UserNotFoundException.class, () -> {
        	  userService.changePassword("test3@test.com", "123123");
          });
          
          String actualMessage2 = exception2.getMessage();
          
          assertTrue(actualMessage2.contains("The user is not found!"));

          
      
	     }

	}

