package com.bridgeit.todoApplication.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TODO_ItemList")
public class ToDoItem implements Serializable {

	@Id
	@GenericGenerator(name = "abc", strategy = "increment")
	@GeneratedValue(generator = "abc")
	@Column
	private long id;
	@Column
	private String title;
	@Column
	private String toDoItemDescription;
	@Column
	private Date toDoCreatedDate;
	@Column
	private Date reminder;
	@Column
	private String color;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private User user;

	public ToDoItem() {
		// TODO Auto-generated constructor stub
	}

	public ToDoItem(long id, String title, String toDoItemDescription, Date toDoCreatedDate,Date reminder,String color) {
		super();
		this.id = id;
		this.title = title;
		this.toDoItemDescription = toDoItemDescription;
		this.toDoCreatedDate = toDoCreatedDate;
		this.reminder=reminder;
		this.color=color;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getToDoItemDescription() {
		return toDoItemDescription;
	}

	public void setToDoItemDescription(String toDoItemDescription) {
		this.toDoItemDescription = toDoItemDescription;
	}

	public Date getToDoCreatedDate() {
		return toDoCreatedDate;
	}

	public void setToDoCreatedDate(Date toDoCreatedDate) {
		this.toDoCreatedDate = toDoCreatedDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getReminder() {
		return reminder;
	}

	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
