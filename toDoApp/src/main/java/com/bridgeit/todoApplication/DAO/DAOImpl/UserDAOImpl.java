package com.bridgeit.todoApplication.DAO.DAOImpl;

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

import com.bridgeit.todoApplication.DAO.UserDAO;
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


	public void addEntity(User user) throws Exception {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}

	public User getEntityById(int id) throws Exception {

		session = sessionFactory.getCurrentSession();
		Criteria ctr = session.createCriteria(User.class);
		ctr.add(Restrictions.eq("id", id));
		User user = (User) ctr.uniqueResult();
		return user;
	}

	public List<User> getUserList() throws Exception {
		session = sessionFactory.getCurrentSession();
		Criteria ctr = session.createCriteria(User.class);

		@SuppressWarnings("unchecked")
		List<User> list = ctr.list();

		return list;
	}


	public void deleteEntity(int id) throws Exception {
		session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from User where id = :id");
		query.setParameter("id", id);
		int rowCount = query.executeUpdate();
		System.out.println(rowCount + " Data Deleted");
	}

	
	public User authUser(String email, String password) {
		session = sessionFactory.getCurrentSession();

		Criteria ctr = session.createCriteria(User.class);
		User user = (User) ctr.add(Restrictions.conjunction().add(Restrictions.eq("email", email))
				.add(Restrictions.eq("password", password))).uniqueResult();
		return user;
	}
}
