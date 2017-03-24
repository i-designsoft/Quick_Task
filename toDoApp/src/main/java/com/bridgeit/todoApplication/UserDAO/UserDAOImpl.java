package com.bridgeit.todoApplication.UserDAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.todoApplication.model.User;

/**
 * This is Implementation class of UserDAO Interface which implements all the method mentioned in UserDAO Interface.
 * @author UmaShankar
 *
 */
@Repository
@Transactional
public class UserDAOImpl implements UserDAO{
	@Autowired
	 SessionFactory sessionFactory;

	 Session session = null;
	 Transaction tx = null;

	 
	/* 
	 * This method used to add an Object of User Entity.
	 * Return: True/False
	 * 
	 */
	
	 public User authUser(String email, String password) {

			Session ses = sessionFactory.getCurrentSession();
			try {
				Criteria cr = ses.createCriteria(User.class);

				User user = (User) cr.add(Restrictions.conjunction().add(Restrictions.eq("email", email))
						.add(Restrictions.eq("password", password))).uniqueResult();
				return user;
			} catch (HibernateException e) {
				e.printStackTrace();
			}
			return null;
		}


	@Override
	public User findById(long id) {
		session=sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from User where id=:id");
		qry.setParameter("id", id);
		User user=(User) qry.uniqueResult();
		return user;
	}


	@Override
	public User findByName(String fullName) {
		session=sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from User where fullName=:fullName");
		qry.setParameter("fullName", fullName);
		User user=(User) qry.uniqueResult();
		return null;
	}


	@Override
	public void saveUser(User user) {
		session=sessionFactory.getCurrentSession();
		session.save(user);
		
	}


	


	@Override
	public void deleteUserById(long id) {
		session=sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete from User where id=:id");
		qry.setParameter("id", id);
		qry.executeUpdate();
		
		
	}


	@Override
	public List<User> findAllUsers() {
		session=sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from User");
		
		
		
		return qry.list();
	}


	

}
