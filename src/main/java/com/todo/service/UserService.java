package com.todo.service;

import com.todo.exception.NullException;
import com.todo.model.User;

public interface UserService {

	User register(User body) throws Exception;
		
	User signIn(String username, String password) throws Exception;
	
	int changePassword(String email, String password);
	
}
