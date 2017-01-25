package cl.kvz.provida.oauth2.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import cl.kvz.provida.oauth2.admin.dao.ITokenDAO;
import cl.kvz.provida.oauth2.admin.model.code.RefreshToken;
import cl.kvz.provida.oauth2.admin.model.code.Token;

/**
 * 
 * @author MCastillo
 *
 */
public class TokenDAOImpl implements ITokenDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Token token) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.persist(token);
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Token verifyToken(String bearer) {
		Session session = this.sessionFactory.openSession();

		Query q = session
				.createQuery("from Token where token = (:token)");
		q.setString("token", bearer);
		
		List<Token> list = (List<Token>) q.list();
		session.close();
		return list.size() == 1 ? list.get(0) : null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Token findLastByApp(int clientId) {
		Session session = this.sessionFactory.openSession();

		Query q = session
				.createQuery("from Token where client = (:clientID) order by dateCreation DESC");
		q.setInteger("clientID", clientId);

		List<Token> list = (List<Token>) q.list();
		session.close();
		return list.size()==0 ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public RefreshToken findLastByToken(int tokenId) {
		Session session = this.sessionFactory.openSession();
	
		Query q = session
		.createQuery("from RefreshToken where token_id = (:tokenId) order by dateCreation DESC");
		q.setInteger("tokenId", tokenId);
	
		List<RefreshToken> list = (List<RefreshToken>) q.list();
		session.close();
		return list.size()==0 ? null : list.get(0);
	}

}
