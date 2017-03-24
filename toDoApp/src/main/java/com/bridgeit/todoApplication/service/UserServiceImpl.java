package com.bridgeit.todoApplication.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.todoApplication.UserDAO.UserDAO;
import com.bridgeit.todoApplication.model.User;

/**
 * This class is the implementation class of UserService Interface. 
 * @author UmaShankar
 *
 */

public class UserServiceImpl implements UserService{
	@Autowired
	UserDAO userDAO;

	
private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	
	
	public User findById(long id) {
		
		return userDAO.findById(id);
	}
	
	public User findByName(String fullName) {
	
		return userDAO.findByName(fullName);
	}
	
	public void saveUser(User user) {
		userDAO.saveUser(user);
	}

	public void deleteUserById(long id) {
		
		userDAO.deleteUserById(id);
	}

	@Override
	public List<User> findAllUsers() {
		
		return userDAO.findAllUsers();
	}

	@Override
	public User authUser(String email, String password) {
		
		return userDAO.authUser(email, password);
	}

}
