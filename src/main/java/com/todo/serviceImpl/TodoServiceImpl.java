package com.todo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import com.todo.model.Todo;
import com.todo.repository.TodoRepository;
import com.todo.repository.UserRepository;
import com.todo.service.TodoService;
import com.todo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepository todoRepository;	
    
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    
	@Override
	public List<Todo> getTodos(String staus) throws Exception {
		 List<Todo> list;
	       
		if(staus!=null)
		{
			int status=Integer.parseInt(staus);
			try {
			
					list = todoRepository.findTodos(status);				
				
			}catch(Exception ex)
			{
				throw ex;
			}
		}
       	else {
				list = todoRepository.findAll();
			}
		
			
		return list;
	}


	@Override
	public boolean createTodo(Todo todo) throws Exception {
		

		return todoRepository.save(todo) instanceof Todo;
	}


}
