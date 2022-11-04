package com.todo.model;

import java.sql.Date;
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
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User userId;
	
	@CreationTimestamp
	@Column(name = "createdAt", updatable = false)
	private Date createdAt;
	
	@UpdateTimestamp
	@Column(name = "updatedAt", updatable = false)
	private Date updatedAt;
	
	
	@Column(name = "status")
	private Status status;
	
	
	/***
	 * 
	 * @author zeriab
	 * ENUM status
	 */
	private enum Status {
		NotStarted,
		OnGoing,
		Completed
		};
		
		
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
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public void setStatus(Status status )
	{
		this.status=status;
	}
	public Status getStatus()
	{
		return status;
	}
	public Date getCreated() {
		return createdAt;
	}
	public void setCreated(Date created) {
		this.createdAt = created;
	}
	public Date getUpdated() {
		return updatedAt;
	}
	public void setUpdated(Date updated) {
		this.updatedAt = updated;
	}
	
	
}
