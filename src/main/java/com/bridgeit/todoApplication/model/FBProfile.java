package com.bridgeit.todoApplication.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FBProfile {
	private String id;
	private String email;
	private String name;
	
	@JsonProperty("picture")
	private Picture picture;

	

	public String getId() {
	return id;
	}
	public void setId(String id) {
	this.id = id;
	}
	public String getEmail() {
	return email;
	}
	public void setEmail(String email) {
	this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "FBProfile [id=" + id + ", email=" + email + ", name=" + name + ", picture=" + picture + "]";
	}
	public Picture getPicture() {
		return picture;
	}
	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	
	
	
}
	