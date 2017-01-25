package cl.kvz.provida.oauth2.admin.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import cl.kvz.provida.oauth2.admin.dao.IUserDAO;
import cl.kvz.provida.oauth2.admin.model.User;


/**
 * 
 * @author MCastillo
 *
 */
public class UserDAOImpl implements IUserDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Override
	public void save(User u) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(u);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> list() {
		Session session = this.sessionFactory.openSession();
		List<User> personList = (List<User>)session.createQuery("from User").list();
		session.close();
		return personList;
	}

	@Override
	public User findById(User u) {
		Session session = this.sessionFactory.openSession();
		User user =  (User) session.get(User.class, u.getId());
		session.close();
		return user;
	}

}
