package com.todo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.todo.app.model.Todo;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

	@Query(value = "Select * from public.\"todo\"  WHERE status=:status", nativeQuery = true)
	List<Todo> findTodos(@Param("status") int status);

}
