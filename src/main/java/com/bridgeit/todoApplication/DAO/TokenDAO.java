package com.bridgeit.todoApplication.DAO;

import com.bridgeit.todoApplication.model.Token;

public interface TokenDAO {
	Token getRefreshToken(String refreshToken);

	void createNewToken(Token token);

	Token getAccessToken(String accessToken);

}
