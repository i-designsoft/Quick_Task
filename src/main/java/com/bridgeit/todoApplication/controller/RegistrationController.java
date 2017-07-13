package com.bridgeit.todoApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.bridgeit.todoApplication.JSON.ErrorResponse;
import com.bridgeit.todoApplication.JSON.Response;
import com.bridgeit.todoApplication.JSON.SignupErrorResponse;
import com.bridgeit.todoApplication.JSON.UserResponse;
import com.bridgeit.todoApplication.model.Status;
import com.bridgeit.todoApplication.model.User;
import com.bridgeit.todoApplication.service.UserService;
import com.bridgeit.todoApplication.validation.UserValidation;
/**
 * Component class called “RegistrationController” with  public methods with various combinations of incoming http request message & return value from simple java primitive to complex types.
 * @author UmaShankar
 *
 */
@RestController
public class RegistrationController {

	@Autowired
	private UserService userservice;

	@Autowired
	private UserValidation userValidation;


	/* Submit form in Spring Restful Services */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody Response addUser(@RequestBody User user,  BindingResult bindingResult) {
		userValidation.validate(user, bindingResult);

		SignupErrorResponse ser = new SignupErrorResponse();
		if (bindingResult.hasErrors()) {
			List<FieldError> list = bindingResult.getFieldErrors();
			ser.setStatus(-1);
			ser.setErrorlist(list);
			return ser;
		}
		
		try {
			userservice.addEntity(user);
			ser.setStatus(1);
			ser.setMessage("User added successfully");
			return ser;
		}
		catch (Exception e) {
		
			ErrorResponse er = new ErrorResponse();
			er.setStatus(-1);
			er.setMessage("Internal server error, please try again.");
			return er;
		}
	}

	/* Ger a single objct in Json form in Spring Rest Services */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Response getEmployeeById(@PathVariable("id") int id) {
		User user = null;
		ErrorResponse er= null;
		try {
			user = userservice.getEntityById(id);

		} catch (Exception e) 
		{
			er = new ErrorResponse();
			er.setStatus(-1);
			er.setMessage("Internal server error, please try again.");
			return er;
		}
		
		UserResponse ur = new UserResponse();
		ur.setUser(user);
		return ur;
	}

	/* Getting List of objects in Json format in Spring Restful Services */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Response getEmployee() {
		ErrorResponse er= null;
		List<User> userList = null;
		try {
			userList = userservice.getEntityList();

		} catch (Exception e) {
			e.printStackTrace();
			er = new ErrorResponse();
			er.setStatus(-1);
			er.setMessage("Internal server error, please try again.");
			return er;
		}
		
		UserResponse ur = new UserResponse();
		ur.setList(userList);
		return ur;
	}

	/* Delete an object from DB in Spring Restful Services */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody Status deleteEmployee(@PathVariable("id") int id) {

		try {
			userservice.deleteEntity(id);
			return new Status(1, "Employee deleted Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}
	}
 
}
