package com.bridgeit.todoApplication.service;

import java.util.List;

import com.bridgeit.todoApplication.model.ToDoItem;

public interface ToDoItemService {

	ToDoItem findById(long id);
	
	ToDoItem findByTitle(String title);
	
	void saveToDoItem(ToDoItem toDoItem);
	
	void deleteToDoItemById(long id);
	
	void updateToDoById(long id);
	
	List<ToDoItem> findAllToDoItemByUserId(long id);


}
