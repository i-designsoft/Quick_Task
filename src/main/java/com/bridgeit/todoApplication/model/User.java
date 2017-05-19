package com.bridgeit.todoApplication.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;




/**
 * This is an Entity Class Containing User Info.Simple Java POJO with member variables and its getter/setter.
 * @author UmaShankar
 *
 */
@Entity
@Table(name = "TODO_USER")

public class User implements Serializable {
	

	@Id
	@GenericGenerator(name="abc",strategy="increment")
	@GeneratedValue(generator="abc")
	@Column
	private long id;
	@Column
	private String fullName;
	@Column
	private String mobile;
	@Column
	private String email;
	@Column
	private String password;
	
	
	
	public User(){
		id=0;
	}

	public User(long id, String fullName, String mobile, String email, String password) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	

	
	
	
}
