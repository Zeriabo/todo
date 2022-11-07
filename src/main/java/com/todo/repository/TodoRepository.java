package com.todo.repository;

import com.todo.model.Todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


	@Repository
	public interface TodoRepository extends JpaRepository<Todo, Long> {
		

		@Query(value="Select * from public.\"todo\"  WHERE status=:status", nativeQuery = true)
	    List<Todo> findTodos(@Param("status") int status);
//		
//		@Query(value="Select * from public.\"user\"  WHERE email=:email", nativeQuery = true)
//	    User getUserByEmail(@Param("email") String email);
//		
//		@Query(value="Update public.\"user\" Set password=:password WHERE email=:email RETURNING id", nativeQuery = true)
//		int changePassword(@Param("email") String email, @Param("password") String password);
}