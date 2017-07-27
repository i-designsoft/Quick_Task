package com.bridgeit.todoApplication.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Picture {

	private FBProfileData data;

	public FBProfileData getData() {
		return data;
	}

	public void setData(FBProfileData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Picture [data=" + data + "]";
	}
	
	
	
}
