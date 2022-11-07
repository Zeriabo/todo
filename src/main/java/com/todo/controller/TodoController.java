package com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.todo.api.AuthResponse;
import com.todo.model.Todo;
import com.todo.model.Todo.Status;
import com.todo.model.User;
import com.todo.repository.UserRepository;
import com.todo.service.TodoService;
import com.todo.service.UserService;
import com.todo.util.JwtTokenUtil;
import java.lang.reflect.InvocationTargetException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import java.util.*;
import java.io.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

	@Autowired
	TodoService todoservice;
	@Autowired
	UserRepository userRepository;

	public TodoController() {

	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "", consumes = { MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })

	public ResponseEntity getTodos(@RequestParam(required = false) String status) throws Exception {

		List todos = todoservice.getTodos(status);

		return ResponseEntity.ok(todos);
	}

	@PostMapping(value = "", consumes = { MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })

	public ResponseEntity<String> createTodos(@RequestBody Todo todo, @RequestParam(required = false) String status)
			throws Exception {

		Todo todoToCreate = new Todo();

		User user = userRepository.findById(todo.getUser().getId()).orElse(new User());

		if (status != null) {
			if (status.equals("Completed")) {
				todoToCreate.setStatus(Status.Completed);
			} else if (status.equals("OnGoing")) {
				todoToCreate.setStatus(Status.OnGoing);
			} else if (status.equals("NotStarted")) {
				todoToCreate.setStatus(Status.NotStarted);
			} else {
				todoToCreate.setStatus(null);
			}

		}
		if (user.getId() != null) {
			if (status != null) {
				todoToCreate.setDescription(todo.getDescription());
				todoToCreate.setName(todo.getName());
				todoToCreate.setUser(user);

			} else {
				todoToCreate.setDescription(todo.getDescription());
				todoToCreate.setName(todo.getName());
				todoToCreate.setUser(user);
				todoToCreate.setStatus(null);
			}
			boolean created = todoservice.createTodo(todoToCreate);
			return ResponseEntity.ok((created) ? "Created: " + todoToCreate.getString() : "Not Created");
		} else {

			// handle Exception inserting without login
			return ResponseEntity.status(500).body("User not found!");
		}

	}

}