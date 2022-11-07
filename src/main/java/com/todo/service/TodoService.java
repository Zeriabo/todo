package com.todo.service;

import com.todo.model.Todo;
import java.util.List;

public interface TodoService {

	List<Todo> getTodos(String status) throws Exception;
	
	boolean createTodo(Todo todo) throws Exception;
}
