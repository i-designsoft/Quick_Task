package com.bridgeit.todoApplication.service.ServiceImpl;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.todoApplication.DAO.UserDAO;
import com.bridgeit.todoApplication.model.User;
import com.bridgeit.todoApplication.service.UserService;

/**
 * This class is the implementation class of UserService Interface. 
 * @author UmaShankar
 *
 */

public class UserServiceImpl implements UserService{
	@Autowired
	UserDAO userDAO;

	
private static final AtomicLong counter = new AtomicLong();
	public void addEntity(User user) throws Exception {
		userDAO.addEntity(user);

	}
	public User getEntityById(int id) throws Exception{
		return userDAO.getEntityById(id);
	}

	public List<User> getEntityList() throws Exception {
		return userDAO.getUserList();
	}

	public void deleteEntity(int id) throws Exception {
		userDAO.deleteEntity(id);
	}

	public User authUser(String email, String password) {
		return userDAO.authUser(email, password);
	}

}
