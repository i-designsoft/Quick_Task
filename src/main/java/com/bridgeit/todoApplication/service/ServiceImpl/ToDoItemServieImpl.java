package com.bridgeit.todoApplication.service.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgeit.todoApplication.DAO.ToDoItemDAO;
import com.bridgeit.todoApplication.model.ToDoItem;
import com.bridgeit.todoApplication.service.ToDoItemService;

public class ToDoItemServieImpl implements ToDoItemService{
	@Autowired
	ToDoItemDAO toDoItemDAO;

	@Override
	public ToDoItem findById(long id) {
		return toDoItemDAO.findById(id);
	}

	@Override
	public ToDoItem findByTitle(String title) {
		return toDoItemDAO.findByTitle(title);
	}

	@Override
	public void saveToDoItem(ToDoItem toDoItem) {
		toDoItemDAO.saveToDoItem(toDoItem);
		
	}

	@Override
	public void deleteToDoItemById(long id) {
		toDoItemDAO.deleteToDoItemById(id);
	}

	@Override
	public void updateToDoById(long id) {
		toDoItemDAO.updateToDoById(id);
		
	}


	@Override
	public List<ToDoItem> findAllToDoItemByUserId(long id) {
		
		return toDoItemDAO.findAllToDoItemByUserId(id);
	}

	@Override
	public List<ToDoItem> findTodaysTask(long id, Date date) {
		
		
		return toDoItemDAO.findTodaysTask(id, date);

	}

	@Override
	public List<ToDoItem> listAllPinnedTask(long id) {
		
		return toDoItemDAO.listAllPinnedTask(id);
	}

	@Override
	public List<ToDoItem> listAllArchivedTask(long id) {
		
		return toDoItemDAO.listAllArchivedTask(id);
	}

	
}
