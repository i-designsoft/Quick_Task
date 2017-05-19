package com.bridgeit.todoApplication.JSON;

import java.util.List;

import org.springframework.validation.FieldError;

public class SignupErrorResponse extends Response {
	String mobError;
	String userNameError;
	String emailErro;
	String passError;
	List<FieldError> errorlist;

	public String getMobError() {
		return mobError;
	}

	public void setMobError(String mobError) {
		this.mobError = mobError;
	}

	public String getUserNameError() {
		return userNameError;
	}

	public void setUserNameError(String userNameError) {
		this.userNameError = userNameError;
	}

	public String getEmailErro() {
		return emailErro;
	}

	public void setEmailErro(String emailErro) {
		this.emailErro = emailErro;
	}

	public String getPassError() {
		return passError;
	}

	public void setPassError(String passError) {
		this.passError = passError;
	}

	public List<FieldError> getErrorlist() {
		return errorlist;
	}

	public void setErrorlist(List<FieldError> errorlist) {
		this.errorlist = errorlist;
	}
	
	
}
