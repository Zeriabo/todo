package com.todo.model;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "todo")
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp
	@Column(name = "createdAt", updatable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "updatedAt", updatable = false)
	private LocalDateTime updatedAt;
	
	
	@Column(name = "status")
	private Status status;
	
	
	/***
	 * 
	 * @author zeriab
	 * ENUM status
	 */
	public  enum Status {
		NotStarted,
		OnGoing,
		Completed
		};
		
	
   public Todo()
   {
	   
   }
  
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	};
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setStatus(Status status )
	{
		this.status=status;
	}
	public Status getStatus()
	{
		return status;
	}
	public LocalDateTime getCreated() {
		return createdAt;
	}
	public void setCreated(LocalDateTime created) {
		this.createdAt = created;
	}
	public LocalDateTime getUpdated() {
		return updatedAt;
	}
	public void setUpdated(LocalDateTime updated) {
		this.updatedAt = updated;
	}
	
	public String getString()
	{
		return "Todo name: "+this.getName()+" Description: "+this.getDescription()+" created at "+this.createdAt;
	}
}
