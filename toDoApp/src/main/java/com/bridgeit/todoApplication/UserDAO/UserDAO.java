package com.bridgeit.todoApplication.UserDAO;

import java.util.List;

import com.bridgeit.todoApplication.model.User;

/**
 * This DAO Interface Contain Several method to interact with DataBase like Inserting Data,Fetching Data from DataBase or Delete from Database 
 * @author bridgeit
 *
 */
public interface UserDAO {
	
	User findById(long id);
	
	User findByName(String fullName);
	
	void saveUser(User user);
	
	void deleteUserById(long id);

	List<User> findAllUsers(); 
	public User authUser(String email, String password);
	
	
	
	

}
