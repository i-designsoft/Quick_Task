package com.bridgeit.todoApplication.JSON;

import java.util.List;

import com.bridgeit.todoApplication.model.User;



public class UserResponse extends Response {
	User user;
	
	List<User> list;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}
	
}
