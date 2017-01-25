package cl.kvz.provida.oauth2.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import cl.kvz.provida.oauth2.admin.dao.ICodeDAO;
import cl.kvz.provida.oauth2.admin.model.code.Code;

/**
 * 
 * @author MCastillo
 *
 */
public class CodeDAOImpl implements ICodeDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Code findLastByApp(int clientId) {
		Session session = this.sessionFactory.openSession();

		Query q = session
				.createQuery("from Code where client = (:clientID) order by dateCreation DESC");
		q.setInteger("clientID", clientId);

		List<Code> codeList = (List<Code>) q.list();
		session.close();
		return codeList.size()==0 ? null : codeList.get(0);
	}


	@Override
	public void save(Code code) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(code);
		tx.commit();
		session.close();
		
	}


	

}
