package com.bridgeit.todoApplication.DAO;

import java.util.List;

import com.bridgeit.todoApplication.model.ToDoItem;

public interface ToDoItemDAO {
	
	ToDoItem findById(long id);
	
	ToDoItem findByTitle(String title);
	
	void saveToDoItem(ToDoItem toDoItem);
	
	void deleteToDoItemById(long id);
	
	void updateToDoById(long id);
	
	List<ToDoItem> findAllToDoItemByUserId(long id);
}
	
	
