package com.bridgeit.todoApplication.service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgeit.todoApplication.DAO.ToDoItemDAO;
import com.bridgeit.todoApplication.model.ToDoItem;
import com.bridgeit.todoApplication.service.ToDoItemService;

public class ToDoItemServieImpl implements ToDoItemService{
	@Autowired
	ToDoItemDAO toDoItemDAO;

	public ToDoItem findById(long id) {
		return toDoItemDAO.findById(id);
	}

	public ToDoItem findByTitle(String title) {
		return toDoItemDAO.findByTitle(title);
	}

	public void saveToDoItem(ToDoItem toDoItem) {
		toDoItemDAO.saveToDoItem(toDoItem);
		
	}

	public void deleteToDoItemById(long id) {
		toDoItemDAO.deleteToDoItemById(id);
	}

	public void updateToDoById(long id) {
		toDoItemDAO.updateToDoById(id);
		
	}


	public List<ToDoItem> findAllToDoItemByUserId(long id) {
		
		return toDoItemDAO.findAllToDoItemByUserId(id);
	}

	
}
