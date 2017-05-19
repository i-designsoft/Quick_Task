package com.bridgeit.todoApplication.DAO.DAOImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.todoApplication.DAO.ToDoItemDAO;
import com.bridgeit.todoApplication.model.ToDoItem;
import com.bridgeit.todoApplication.model.User;
@Repository
@Transactional
public class ToDoItemDAOImpl implements ToDoItemDAO{

	
	@Autowired
	 SessionFactory sessionFactory;

	 Session session = null;
	 Transaction tx = null;

	 
	public ToDoItem findById(long id) {
		
		session=sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from ToDoItem where id=:id");
		qry.setParameter("id", id);
		ToDoItem toDoItem=(ToDoItem) qry.uniqueResult();
		return toDoItem;
	}

	public ToDoItem findByTitle(String title) {
		session=sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from ToDoItem where title=:title");
		qry.setParameter("title", title);
		ToDoItem toDoItem=(ToDoItem)qry.uniqueResult();
		return toDoItem;
	}

	public void saveToDoItem(ToDoItem toDoItem) {
		session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(toDoItem);
		
		
	}

	public void deleteToDoItemById(long id) {
		session=sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete from ToDoItem where id=:id");
		qry.setParameter("id", id);
		qry.executeUpdate();
		
		
	}

	public void updateToDoById(long id) {
		
		
	}


public List<ToDoItem> findAllToDoItemByUserId(long id) {
	
	Session session = sessionFactory.openSession();

            
	Criteria ctr = session.createCriteria(ToDoItem.class);
	List<ToDoItem> toDoList = ctr.add(Restrictions.eq("user.id", id)).list();
	session.close();
	
	if( toDoList != null)
	{
		for (ToDoItem toDoItem : toDoList) {
			if( toDoItem.getUser() != null){
				toDoItem.setUser(null);
			}
		}
	}
	
	return toDoList;
}


}
