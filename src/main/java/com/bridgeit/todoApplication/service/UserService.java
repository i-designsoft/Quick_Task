package com.bridgeit.todoApplication.service;

import java.util.List;

import com.bridgeit.todoApplication.model.User;

/**
 * UserService is an Interface Containing all service Method . 
 * @author UmaShankar
 *
 */
public interface UserService {

	
	void addEntity(User user) throws Exception;

	User getEntityById(int id) throws Exception;

	List<User> getEntityList() throws Exception;

	void deleteEntity(int id) throws Exception;

	User authUser(String email, String password);

	public User getEntityByEmailId(String email);
}
