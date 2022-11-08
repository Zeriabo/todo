package com.todo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Component;
import com.todo.exception.CredentialsException;
import com.todo.exception.NullException;
import com.todo.exception.UserAlreadyExistsException;
import com.todo.exception.UserNotFoundException;
import com.todo.model.User;
import com.todo.repository.UserRepository;
import com.todo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public User register(User user) throws Exception {
		
		User userIfExisted = userRepository.getUserByEmail(user.getEmail());
		
		if (userIfExisted instanceof User) {
			throw new UserAlreadyExistsException();
		}

		user.setPassword(user.getPassword());

		try {
			Object obj = userRepository.save(user);
	         
			return (User) obj;
			
		} catch (Exception ex) {
			
			throw ex;
		}

	}

	@Override
	public User signIn(String email, String password) throws Exception {
		
		if (email == null || password == null) {
          throw new NullException();
		}
		try {
			User user = userRepository.loginUser(email, password);
			
			if (user != null) {
				
				return user;
				
			} else {

				throw new CredentialsException();
			}
		}catch(Exception ex)
		{
			throw ex;
		}
		
		
		

	}

	@Override
	public boolean changePassword(String email, String password) throws Exception {
		
		
		User user = userRepository.getUserByEmail(email);
		
		if(user instanceof User)
		{
			return userRepository.changePassword(email, password)>0;
			
		}else {
			throw new UserNotFoundException();
		}
		

	}

}
