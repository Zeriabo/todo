package com.todo;

import static org.assertj.core.api.Assertions.assertThat;
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
	    private static UserService userService;
	    
	    @MockBean
	    private UserRepository userRepository;
	    
	    @Test
	    public void registerUser() throws Exception {
          User user = new User();
          user.setEmail("test@test.com");
          user.setPassword("test");
          
          userService.register(user);
          
         
          Exception exception = assertThrows(UserAlreadyExistsException.class, () -> {
        	  userService.register(user);
          });
          
          String actualMessage = exception.getMessage();
          
          assertTrue(actualMessage.contains("The user already exists!"));
	     }
	    
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
          
          Exception exception2 = assertThrows(CredentialsException.class, () -> {
        	  userService.signIn("asdlkjaslkdjas","jakshdkjashdjk");
          });
          
          String actualMessage2 = exception2.getMessage();
          
          assertTrue(actualMessage2.contains("The user or password wrong!"));
          
          
	     }
/*
 * 
 * 	

					@Override
					public User signIn(String username, String password) {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public int changePassword(String email, String password) {
						// TODO Auto-generated method stub
						return 0;
					}
	                // implement methods
	            };
 */
	}

