package com.bridgeit.todoApplication.DAO.DAOImpl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.todoApplication.DAO.TokenDAO;
import com.bridgeit.todoApplication.model.Token;
@Repository
@Transactional
public class TokenDAOImpl implements TokenDAO {

	@Autowired
	SessionFactory sessionfactory;

	
	public Token getRefreshToken(String refreshToken) {
		Session session = sessionfactory.getCurrentSession();
		try {
			Criteria cr = session.createCriteria(Token.class);
			Token token = (Token) cr.add(Restrictions.conjunction().add(Restrictions.eq("refreshToken", refreshToken)))
					.uniqueResult();
			return token;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void createNewToken(Token token) {
		Session session = sessionfactory.getCurrentSession();

		try {
			session.save(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public Token getAccessToken(String accessToken) {
		Session session = sessionfactory.getCurrentSession();
		try {
			Criteria cr = session.createCriteria(Token.class);
			Token token = (Token) cr.add(Restrictions.conjunction().add(Restrictions.eq("accessToken", accessToken)))
					.uniqueResult();
			return token;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	
	}

}
