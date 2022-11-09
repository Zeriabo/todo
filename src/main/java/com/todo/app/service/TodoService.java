package com.todo.app.service;


import java.util.List;

import com.todo.app.model.Todo;

public interface TodoService {

	List<Todo> getTodos(String status) throws Exception;
	
	boolean createTodo(Todo todo) throws Exception;
	

}
