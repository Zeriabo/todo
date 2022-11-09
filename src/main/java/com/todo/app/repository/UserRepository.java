package com.todo.app.repository;

import com.todo.app.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


	@Repository
	public interface UserRepository extends JpaRepository<User, Long> {
		

		@Query(value="Select * from public.\"user\"  WHERE email=:email and password=:password", nativeQuery = true)
	    User loginUser(@Param("email") String email, @Param("password") String password);
		
		@Query(value="Select * from public.\"user\"  WHERE email=:email", nativeQuery = true)
	    User getUserByEmail(@Param("email") String email);
		
		@Query(value="Update public.\"user\" Set password=:password WHERE email=:email RETURNING id", nativeQuery = true)
		int changePassword(@Param("email") String email, @Param("password") String password);
}
