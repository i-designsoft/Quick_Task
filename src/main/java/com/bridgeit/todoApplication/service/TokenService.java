package com.bridgeit.todoApplication.service;

import com.bridgeit.todoApplication.model.Token;

public interface TokenService {

	Token getRefreshToken(String refreshToken);

	void createNewToken(Token token);

	Token getAccessToken(String accessToken);

}
