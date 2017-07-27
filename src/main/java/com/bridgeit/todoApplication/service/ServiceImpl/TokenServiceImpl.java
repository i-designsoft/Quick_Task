package com.bridgeit.todoApplication.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.todoApplication.DAO.TokenDAO;
import com.bridgeit.todoApplication.model.Token;
import com.bridgeit.todoApplication.service.TokenService;

public class TokenServiceImpl implements TokenService{

	@Autowired 
	private TokenDAO tokenDAO;
	@Override
	public Token getRefreshToken(String refreshToken) {
		return tokenDAO.getRefreshToken(refreshToken);
	}

	@Override
	public void createNewToken(Token token) {
		tokenDAO.createNewToken(token);
		
	}

	@Override
	public Token getAccessToken(String accessToken) {
		
		return tokenDAO.getAccessToken(accessToken);
	}

}
