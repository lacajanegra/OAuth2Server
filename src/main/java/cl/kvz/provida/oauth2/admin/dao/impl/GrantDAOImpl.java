package cl.kvz.provida.oauth2.admin.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import cl.kvz.provida.oauth2.admin.dao.IGrantDAO;
import cl.kvz.provida.oauth2.admin.model.Grant;


/**
 * 
 * @author MCastillo
 *
 */
public class GrantDAOImpl implements IGrantDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Override
	public void save(Grant u) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(u);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Grant> list() {
		Session session = this.sessionFactory.openSession();
		List<Grant> personList = (List<Grant>)session.createQuery("from Grant").list();
		session.close();
		return personList;
	}

}
