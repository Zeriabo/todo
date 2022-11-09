package com.todo.app.service;

import com.todo.app.model.User;

public interface UserService {

	User register(User body) throws Exception;
		
	User signIn(String username, String password) throws Exception;
	
	boolean changePassword(String email, String password) throws Exception;
	
}
