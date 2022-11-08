package com.todo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import com.todo.controller.TodoController;
import com.todo.controller.UserController;

@SpringBootTest
class TodoApplicationTests {

	@Autowired
	private TodoController todoControler;
	
	@Autowired
	private UserController userController;
	
	@Test
	void contextLoads() {
		
		assertThat(todoControler).isNotNull();
		assertThat(userController).isNotNull();
	}

}
