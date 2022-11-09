package com.todo.app.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.todo.app.model.Todo;
import com.todo.app.repository.TodoRepository;
import com.todo.app.service.TodoService;
import com.todo.app.service.UserService;
import java.util.List;
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
		// TODO Auto-generated method stub
		return false;
	}









}
