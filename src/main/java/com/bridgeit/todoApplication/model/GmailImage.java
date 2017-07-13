package com.bridgeit.todoApplication.model;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties
public class GmailImage {
private String url;
	
	public String getUrl() 
	{
		return url;
	}
	
	public void setUrl(String url) 
	{
		this.url = url;
	}
	

}
