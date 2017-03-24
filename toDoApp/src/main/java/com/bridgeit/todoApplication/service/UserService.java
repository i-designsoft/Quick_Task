package com.bridgeit.todoApplication.service;

import java.util.List;

import com.bridgeit.todoApplication.model.User;

/**
 * UserService is an Interface Containing all service Method . 
 * @author UmaShankar
 *
 */
public interface UserService {

	
User findById(long id);
	
	User findByName(String fullName);
	
	void saveUser(User user);
	
	void deleteUserById(long id);

	List<User> findAllUsers(); 
	public User authUser(String email, String password);
}
