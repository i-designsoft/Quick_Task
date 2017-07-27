package com.bridgeit.todoApplication.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bridgeit.todoApplication.model.ToDoItem;
import com.bridgeit.todoApplication.model.User;
import com.bridgeit.todoApplication.service.ToDoItemService;

/**
 * @author Uma Shankar
 * REST Controller for backend API which contain different method to handle backend operation.
 * It contain Handler for different client request.
 *
 */
@RestController
public class ToDoItemController {
	
	@Autowired
    ToDoItemService toDoItemService;
	
	//-------------------------------------Retrieve All ToDoItem--------------------------------------
    
    /**
     * Method return all task Corresponding to Logged in User.
     * @param request 
     * @return List<ToDoItem> of Task along with HttpStatus.
     */
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
    
 
 //-----------------------------Retrieve Single ToDoItem-----------------------------------------------
    
    /**Method return all task for Current Date Corresponding to Logged in User.
     * @param request
     * @return List<ToDoItem> of Task along with HttpStatus
     */
    @RequestMapping(value = "/todayTaskList", method = RequestMethod.GET)
    public ResponseEntity<List<ToDoItem>> listAllTodaysTask(HttpServletRequest request) {
    	HttpSession session=request.getSession();
    	Date date=new Date();
    	User user=(User) session.getAttribute("user");
        List<ToDoItem> toDoList = toDoItemService.findTodaysTask(user.getId(), date);
        
        if(toDoList.isEmpty()){
            return new ResponseEntity<List<ToDoItem>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ToDoItem>>(toDoList, HttpStatus.OK);
    }
 
   
   
    
    //-------------------Retrieve Single ToDoItem--------------------------------------------------------
     
    /**Method return Single Task Corresponding to Task Id..
     * @param id
     * @return Object <ToDoItem> along with HttpStatus
     */
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
     
    /**
     * Creating Task according to the Object which is passed from frontend(client).
     * @param toDoItem
     * @param ucBuilder
     * @param request
     * @return HttpStatus
     */
    @RequestMapping(value = "/toDoItem", method = RequestMethod.POST)
    public ResponseEntity<Void> createToDoItem(@RequestBody ToDoItem toDoItem, UriComponentsBuilder ucBuilder,HttpServletRequest request) {
        System.out.println("Creating ToDoItem " + toDoItem.getTitle());
 
        /*if (toDoItem.equals(toDoItemService.findByTitle(toDoItem.getTitle()))) {
            System.out.println("A ToDoItem with name " +toDoItem.getTitle() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/
		HttpSession session = request.getSession();
        Date date = new Date();
        toDoItem.setIsPinned("false");
        toDoItem.setIsArchive("false");
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
 
  //------------------- update a ToDo Item --------------------------------------------------------
    
    @RequestMapping(value = "/updateToDoItem/{id}", method = RequestMethod.POST)
	public ResponseEntity<ToDoItem> updateTask(@RequestBody ToDoItem todo, @PathVariable("id") int taskId, HttpServletRequest request) {

		
		HttpSession sess = request.getSession();
		User user = (User) sess.getAttribute("user");
		System.out.println(user.toString());
		todo.setUser(user);
		todo.setId(taskId);
		/*if(todo.isPinned()==null){
			todo.setPinned("true");
		}else{
			todo.setPinned("false");
		}*/
		todo.setToDoCreatedDate(new Date());
		System.out.println("inside update");

		try {
			toDoItemService.saveToDoItem(todo);
			
			return new ResponseEntity<ToDoItem>(HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<ToDoItem>(HttpStatus.NOT_MODIFIED);
		}
	}
    
    //-----------------------Finding Current Logged in User----------------
    
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseEntity<User> findUser(HttpServletRequest request) {
    	HttpSession session=request.getSession();
    	User user=(User) session.getAttribute("user");
        
        if(user==null){
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //-----------------Make a Task as Pinned-------------------------------
    
    @RequestMapping(value = "/pinnedTask/{id}", method = RequestMethod.POST)
    public ResponseEntity<ToDoItem> pinnedItem(@RequestBody ToDoItem todo, @PathVariable("id") int taskId, HttpServletRequest request) {
    	HttpSession session=request.getSession();
    	User user=(User) session.getAttribute("user");
        
    	todo.setUser(user);
		todo.setId(taskId);
		todo.setToDoCreatedDate(new Date());
		todo.setIsPinned("true");
		System.out.println("inside pined");

		try {
			toDoItemService.saveToDoItem(todo);
			
			return new ResponseEntity<ToDoItem>(HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<ToDoItem>(HttpStatus.NOT_MODIFIED);
		}
    }
    //------------------make Unpinned Task----------------------------------
    
    @RequestMapping(value = "/unPinnedTask/{id}", method = RequestMethod.POST)
    public ResponseEntity<ToDoItem> unPinnedItem(@RequestBody ToDoItem todo, @PathVariable("id") int taskId, HttpServletRequest request) {
    	HttpSession session=request.getSession();
    	User user=(User) session.getAttribute("user");
        
    	todo.setUser(user);
		todo.setId(taskId);
		todo.setToDoCreatedDate(new Date());
		todo.setIsPinned("false");
		System.out.println("inside pined");

		try {
			toDoItemService.saveToDoItem(todo);
			
			return new ResponseEntity<ToDoItem>(HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<ToDoItem>(HttpStatus.NOT_MODIFIED);
		}
    }
    
 //------------------make Unarchived Task----------------------------------
    
    @RequestMapping(value = "/unArchiveTask/{id}", method = RequestMethod.POST)
    public ResponseEntity<ToDoItem> unArchiveTask(@RequestBody ToDoItem todo, @PathVariable("id") int taskId, HttpServletRequest request) {
    	HttpSession session=request.getSession();
    	User user=(User) session.getAttribute("user");
        
    	todo.setUser(user);
		todo.setId(taskId);
		todo.setToDoCreatedDate(new Date());
		todo.setIsArchive("false");
		System.out.println("inside Unarchived");

		try {
			toDoItemService.saveToDoItem(todo);
			
			return new ResponseEntity<ToDoItem>(HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<ToDoItem>(HttpStatus.NOT_MODIFIED);
		}
    }
    
    //----------------------Find Pinned Task--------------------------------
    
    @RequestMapping(value = "/pinnedTaskList", method = RequestMethod.GET)
    public ResponseEntity<List<ToDoItem>> listAllPinnedItems(HttpServletRequest request) {
    	HttpSession session=request.getSession();
    	User user=(User) session.getAttribute("user");
        List<ToDoItem> pinnedTaskList = toDoItemService.listAllPinnedTask(user.getId());
        
        if(pinnedTaskList.isEmpty()){
            return new ResponseEntity<List<ToDoItem>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ToDoItem>>(pinnedTaskList, HttpStatus.OK);
    }
    
//--------------------------Make a Task as Archive-------------------------------
    
    @RequestMapping(value = "/archiveTask/{id}", method = RequestMethod.POST)
    public ResponseEntity<ToDoItem> archiveItem(@RequestBody ToDoItem todo, @PathVariable("id") int taskId, HttpServletRequest request) {
    	HttpSession session=request.getSession();
    	User user=(User) session.getAttribute("user");
        
    	todo.setUser(user);
		todo.setId(taskId);
		todo.setIsArchive("true");
		System.out.println("inside Archive");

		try {
			toDoItemService.saveToDoItem(todo);
			
			return new ResponseEntity<ToDoItem>(HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<ToDoItem>(HttpStatus.NOT_MODIFIED);
		}
    }
    
//----------------------Find A srchived Task--------------------------------
    
    @RequestMapping(value = "/archivedTaskList", method = RequestMethod.GET)
    public ResponseEntity<List<ToDoItem>> listAllArchivedItems(HttpServletRequest request) {
    	HttpSession session=request.getSession();
    	User user=(User) session.getAttribute("user");
        List<ToDoItem> archivedTaskList = toDoItemService.listAllArchivedTask(user.getId());
        
        if(archivedTaskList.isEmpty()){
            return new ResponseEntity<List<ToDoItem>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ToDoItem>>(archivedTaskList, HttpStatus.OK);
    }
    
}
