package com.bridgeit.todoApplication.JSON;

public class TokenResponse extends Response{
	
	private String accessToken;
	private String refreshToken;
	
	public TokenResponse() {}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
}
