package com.bridgeit.todoApplication.service.ServiceImpl;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Override
	public void addEntity(User user) throws Exception {
		userDAO.addEntity(user);

	}
	@Override
	public User getEntityById(int id) throws Exception{
		return userDAO.getEntityById(id);
	}

	@Override
	public List<User> getEntityList() throws Exception {
		return userDAO.getUserList();
	}

	@Override
	public void deleteEntity(int id) throws Exception {
		userDAO.deleteEntity(id);
	}

	@Override
	public User authUser(String email, String password) {
		return userDAO.authUser(email, password);
	}
	@Override
	public User getEntityByEmailId(String email) {
		
		return userDAO.getEntityByEmailId(email);
	}

}
