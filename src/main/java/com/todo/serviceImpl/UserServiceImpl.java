package com.todo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.todo.model.User;
import com.todo.repository.UserRepository;
import com.todo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;	
    
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    
	@Override
	public User register(User body) throws Exception {
		
		User user = new User();
		user.setEmail(body.getEmail());
		user.setPassword(body.getPassword());
        
		try {
			Object obj = userRepository.save(user);
			if(obj instanceof User)
			{
				 return (User)obj;
			}
			else {
				return null;
			}
		}catch(Exception ex)
		{
			throw ex;
		}
		
		
		
		
	}

	@Override
	public boolean signIn(String username, String password) {
		

		userRepository.findOne(null)
		return false;
	}

}
