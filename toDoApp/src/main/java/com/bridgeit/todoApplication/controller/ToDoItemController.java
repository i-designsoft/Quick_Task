package com.bridgeit.todoApplication.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bridgeit.todoApplication.JSON.ErrorResponse;
import com.bridgeit.todoApplication.JSON.Response;
import com.bridgeit.todoApplication.model.ToDoItem;
import com.bridgeit.todoApplication.model.User;
import com.bridgeit.todoApplication.service.ToDoItemService;

@RestController
public class ToDoItemController {
	
	@Autowired
    ToDoItemService toDoItemService;
	
	//-------------------Retrieve All ToDoItem--------------------------------------------------------
    
    @RequestMapping(value = "/toDoList", method = RequestMethod.GET)
    public ResponseEntity<List<ToDoItem>> listAllToDoItems(HttpServletRequest request) {
    	HttpSession session=request.getSession();
    	User user=(User) session.getAttribute("user");
        List<ToDoItem> toDoList = toDoItemService.findAllToDoItemByUserId(user.getId());
        if(toDoList.isEmpty()){
            return new ResponseEntity<List<ToDoItem>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ToDoItem>>(toDoList, HttpStatus.OK);
    }
 
 
    
   
    
    //-------------------Retrieve Single ToDoItem--------------------------------------------------------
     
    @RequestMapping(value = "/toDoItem/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDoItem> getToDoItem(@PathVariable("id") long id) {
        System.out.println("Fetching ToDoItem with id " + id);
        ToDoItem toDoItem= toDoItemService.findById(id);
        if (toDoItem == null) {
            System.out.println("ToDoItem with id " + id + " not found");
            return new ResponseEntity<ToDoItem>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ToDoItem>(toDoItem, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a ToDo Item--------------------------------------------------------
     
    @RequestMapping(value = "/toDoItem", method = RequestMethod.POST)
    public ResponseEntity<Void> createToDoItem(@RequestBody ToDoItem toDoItem, UriComponentsBuilder ucBuilder,HttpServletRequest request) {
        System.out.println("Creating ToDoItem " + toDoItem.getTitle());
 
        /*if (toDoItem.equals(toDoItemService.findByTitle(toDoItem.getTitle()))) {
            System.out.println("A ToDoItem with name " +toDoItem.getTitle() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/
		HttpSession session = request.getSession();
        Date date = new Date();
        toDoItem.setToDoCreatedDate(date);
        toDoItem.setUser((User) session.getAttribute("user"));
 
        toDoItemService.saveToDoItem(toDoItem);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/toDoItem/{id}").buildAndExpand(toDoItem.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
 
    
     
    
    
    
    //------------------- Delete a ToDo Item --------------------------------------------------------
     
    @RequestMapping(value = "/toDoItem/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ToDoItem> deleteToDoItem(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting ToDo Item with id " + id);
 
       ToDoItem toDoItem=toDoItemService.findById(id);
        if (toDoItem == null) {
            System.out.println("Unable to delete. ToDo Item with id " + id + " not found");
            return new ResponseEntity<ToDoItem>(HttpStatus.NOT_FOUND);
        }
 
       toDoItemService.deleteToDoItemById(id);
        return new ResponseEntity<ToDoItem>(HttpStatus.NO_CONTENT);
    }
 
    @RequestMapping(value = "toDoUpdate/{id}", method = RequestMethod.POST)
	public ResponseEntity<Void> updateToDoItem(@RequestBody ToDoItem todo, @PathVariable("id") int toDoId,UriComponentsBuilder ucBuilder, HttpServletRequest request) {


		HttpSession sess = request.getSession();
		User user = (User) sess.getAttribute("user");
		todo.setUser(user);
		todo.setId(toDoId);
		todo.setToDoCreatedDate(new Date());
		
		System.out.println("update");
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/toDoItem/{id}").buildAndExpand(todo.getId()).toUri());
		try {
			toDoItemService.saveToDoItem(todo);
			
			 
		        return new ResponseEntity<Void>(headers, HttpStatus.OK);
		} catch (Exception e) {
			 return new ResponseEntity<Void>(headers, HttpStatus.NOT_FOUND);
		}
    }

}
