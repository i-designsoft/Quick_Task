package com.bridgeit.todoApplication.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.todoApplication.JSON.ErrorResponse;
import com.bridgeit.todoApplication.JSON.LoginResponse;
import com.bridgeit.todoApplication.JSON.Response;
import com.bridgeit.todoApplication.JSON.TokenResponse;
import com.bridgeit.todoApplication.model.ToDoItem;
import com.bridgeit.todoApplication.model.User;
import com.bridgeit.todoApplication.service.UserService;

@RestController
public class LoginController {
	

	@Autowired
    UserService userservice;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Response getEmployeeById(@RequestBody Map<String, String> loginMap, HttpServletRequest request,
			HttpServletResponse response) {
		User user = null;

		HttpSession session = request.getSession();
		try {
			user = userservice.authUser(loginMap.get("email"), loginMap.get("password"));
			
		} catch (Exception e) {
			
			ErrorResponse er = new ErrorResponse();
			er.setStatus(-1);
			er.setMessage("Internal server error, please try again.");
			return er;
		}

		if (user == null) {

			ErrorResponse er = new ErrorResponse();
			er.setStatus(-1);
			er.setMessage("Invalid credential, Please check email or password");
			return er;
		}
		session.setAttribute("user", user);
		LoginResponse lr = new LoginResponse();
		lr.setStatus(1);
		lr.setMessage("User logged succesfully");
		TokenResponse tr = new TokenResponse();
		tr.getAccessToken();
		tr.getRefreshToken();
		tr.setStatus(1);

		
		return tr;
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ResponseEntity<Boolean> logoutUser(HttpServletRequest request){
		
		HttpSession sesion = request.getSession();
		sesion.invalidate();
		sesion = request.getSession();
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
}
