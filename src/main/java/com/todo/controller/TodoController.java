package com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.exception.UserNotFoundException;
import com.todo.model.Todo;
import com.todo.model.Todo.Status;
import com.todo.model.User;
import com.todo.repository.TodoRepository;
import com.todo.repository.UserRepository;
import com.todo.service.TodoService;
import java.util.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

	@Autowired
	TodoService todoservice;
	@Autowired
	UserRepository userRepository;
	@Autowired
	TodoRepository todoRepository;

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
             
			throw new UserNotFoundException();
		
		}

	}

	@PutMapping(value = "", consumes = { MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })

	public ResponseEntity<Object> updateTodos(@RequestParam(required = true) long id, @RequestBody Todo todo)
			throws Exception {

		Todo newTodo = todo;
		newTodo.setId(id);
		Optional<Todo> toBeUpdated = todoRepository.findById(id);

		if (toBeUpdated.isPresent()) {
			Todo todoupdatyyed = todoRepository.save(newTodo);
			return ResponseEntity.ok(todoupdatyyed);
		} else {
			return ResponseEntity.status(404).body("Todo is not found!");
		}
	}
	
      @DeleteMapping(value = "", consumes = { MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })

	public ResponseEntity<Object> deleteTodo(@RequestParam(required = true) long id)
			throws Exception {

		Optional<Todo> toBeDeleted = todoRepository.findById(id);

		if (toBeDeleted.isPresent()) {
		       todoRepository.deleteById(toBeDeleted.get().getId());
			return ResponseEntity.ok(toBeDeleted + "Deleted");
		} else {
			return ResponseEntity.status(404).body("Todo is not found!");
		}

	}

}