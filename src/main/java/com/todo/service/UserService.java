package com.todo.service;

import org.springframework.http.ResponseEntity;

import com.todo.model.User;

public interface UserService {
	public User register(User body) throws Exception;
	
	boolean signIn(String username, String password);
}
