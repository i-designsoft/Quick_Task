package com.bridgeit.facebook;

import java.io.IOException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.bridgeit.todoApplication.model.FBProfile;
import com.bridgeit.todoApplication.model.FBToken;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Facebook {
	public String FB_CLIENT_ID = "455615694791739";
	public String FB_SECRET_KEY = "dc57376ed511a5b2c42934fc4cc552dc";
	public String FB_RERDIRECT_URI = "/postfacebooklogin";
	public String FB_URL = "https://www.facebook.com/v2.9/dialog/oauth?client_id=%s&redirect_uri=%s&state=%s&response_type=code&scope=public_profile,email";
	
	public String FB_ACCESS_TOKEN_URL = "https://graph.facebook.com/v2.9/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";
	public String FB_GET_USER_URL= "https://graph.facebook.com/v2.9/me?access_token=%s&fields=id,name,email,picture.type(large)";
	
	public String getFBUrl( String appUrl, String pState) 
	
	
	{
		appUrl = appUrl + FB_RERDIRECT_URI;
		
		return FB_URL.format(FB_URL, new String[]{ FB_CLIENT_ID, appUrl, pState });
		
	}

	public FBProfile authUser(String authCode, String appUrl) throws JsonParseException, JsonMappingException, IOException
	{
		String accessToken = getAccessToken(authCode, appUrl);
		return getUserProfile(accessToken);
	}
	
	public String getAccessToken(String authCode, String appUrl) throws JsonParseException, JsonMappingException, IOException 
	{
		appUrl = appUrl + FB_RERDIRECT_URI;
		String accTokenUrl = FB_URL.format(FB_ACCESS_TOKEN_URL, new String[]{ FB_CLIENT_ID, FB_SECRET_KEY, appUrl, authCode });
		  
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target( accTokenUrl );
		 
		Response response =  target.request().accept(MediaType.APPLICATION_JSON).get();
		//FBToken fbToken = response.readEntity(FBToken.class);
		
		String sr = response.readEntity(String.class);
	/*	System.out.println( sr );*/
		ObjectMapper mapper = new ObjectMapper();
		FBToken fbToken = mapper.readValue(sr, FBToken.class);
		
		client.close();
		
		return fbToken.getAccess_token();
	}
	
	public FBProfile getUserProfile(String accessToken) throws JsonParseException, JsonMappingException, IOException 
	{
		String accProfileUrl = String.format(FB_GET_USER_URL, new String[]{ accessToken });
		  
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target( accProfileUrl );
		 
		Response response =  target.request().accept(MediaType.APPLICATION_JSON).get();
		System.out.println( response );
		//FBProfile profile = response.readEntity(FBProfile.class);
		
		String sr = response.readEntity(String.class);
		System.out.println( sr );
		ObjectMapper mapper = new ObjectMapper();
		FBProfile profile = mapper.readValue(sr, FBProfile.class);
		System.out.println(profile.getName());
		
		
		//client.close();
		
		return profile;
	}
	
}
