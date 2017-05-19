package com.bridgeit.todoApplication.DAO;

import java.util.List;

import com.bridgeit.todoApplication.model.User;

/**
 * This DAO Interface Contain Several method to interact with DataBase like Inserting Data,Fetching Data from DataBase or Delete from Database 
 * @author bridgeit
 *
 */
public interface UserDAO {
	
	void addEntity(User user) throws Exception;

	User getEntityById(int id) throws Exception;

	List<User> getUserList() throws Exception;

	void deleteEntity(int id) throws Exception;

	User authUser(String email, String password);

}
