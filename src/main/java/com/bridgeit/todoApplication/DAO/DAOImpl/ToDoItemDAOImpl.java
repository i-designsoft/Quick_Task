package com.bridgeit.todoApplication.DAO.DAOImpl;

import java.util.Date;
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

@Repository
@Transactional
public class ToDoItemDAOImpl implements ToDoItemDAO {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public ToDoItem findById(long id) {

		session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from ToDoItem where id=:id");
		qry.setParameter("id", id);
		ToDoItem toDoItem = (ToDoItem) qry.uniqueResult();
		return toDoItem;
	}

	@Override
	public ToDoItem findByTitle(String title) {
		session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from ToDoItem where title=:title");
		qry.setParameter("title", title);
		ToDoItem toDoItem = (ToDoItem) qry.uniqueResult();
		return toDoItem;
	}

	@Override
	public void saveToDoItem(ToDoItem toDoItem) {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(toDoItem);

	}

	@Override
	public void deleteToDoItemById(long id) {
		session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete from ToDoItem where id=:id");
		qry.setParameter("id", id);
		qry.executeUpdate();

	}

	@Override
	public void updateToDoById(long id) {

	}

	@Override
	public List<ToDoItem> findAllToDoItemByUserId(long id) {

		Session session = sessionFactory.openSession();

		Criteria ctr = session.createCriteria(ToDoItem.class);
		ctr.add(Restrictions.eq("user.id", id));
		/*ctr.add(Restrictions.or(Restrictions.isNull("isPinned"), Restrictions.eq("isPinned", "false")));
		ctr.add(Restrictions.or(Restrictions.isNull("isArchive"), Restrictions.eq("isArchive", "false")));*/
		List<ToDoItem> toDoList = ctr.list();
		session.close();

		if (toDoList != null) {
			for (ToDoItem toDoItem : toDoList) {
				if (toDoItem.getUser() != null) {
					toDoItem.setUser(null);
				}
			}
		}

		return toDoList;
	}

	@Override
	public List<ToDoItem> findTodaysTask(long id, Date date) {

		Session session = sessionFactory.openSession();

		Criteria ctr = session.createCriteria(ToDoItem.class);
		List<ToDoItem> todaysTaskList = ctr.add(Restrictions.eq("toDoItem.reminder", date)).list();
		session.close();

		if (todaysTaskList != null) {
			for (ToDoItem toDoItem : todaysTaskList) {
				if (toDoItem.getReminder() != null) {
					toDoItem.setReminder(null);
				}
			}
		}

		return todaysTaskList;
	}

	@Override
	public List<ToDoItem> listAllPinnedTask(long id) {
		session = sessionFactory.getCurrentSession();
		
		Query qry = session.createQuery("from ToDoItem where (isPinned = :isPinned) AND (user.id = :id)");
		qry.setParameter("id", id);
		qry.setParameter("isPinned", "true");
		List<ToDoItem> pinnedTaskList = qry.list();
		return pinnedTaskList;
	}

	@Override
	public List<ToDoItem> listAllArchivedTask(long id) {
		session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from ToDoItem where (isArchive = :isArchive) AND (user.id = :id)");
		qry.setParameter("id", id);
		qry.setParameter("isArchive", "true");
		List<ToDoItem> archivedTaskList = qry.list();
		return archivedTaskList;
	}

}
