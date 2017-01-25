package cl.kvz.provida.oauth2.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import cl.kvz.provida.oauth2.admin.dao.IClientDAO;
import cl.kvz.provida.oauth2.admin.model.Client;
import cl.kvz.provida.oauth2.admin.model.code.Token;
import cl.kvz.provida.oauth2.core.util.Constants;

/**
 * 
 * @author MCastillo
 *
 */
public class ClientDAOImpl implements IClientDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Client c) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(c);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> list() {
		Session session = this.sessionFactory.openSession();
		List<Client> clientList = (List<Client>) session.createQuery(
				"from Client").list();
		session.close();
		return clientList;
	}

	@Override
	public void update(Client c) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Query q = session
				.createQuery("delete from Uri where client_id in (:id) ");
		q.setString("id", String.valueOf(c.getId()));
		q.executeUpdate();

		session.update(c);
		tx.commit();
		session.close();

	}

	@Override
	public void delete(Client c) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Query q = session
				.createQuery("update Client set state=(:state) where id = (:id) ");
		q.setString("state", Constants.DELETED);
		q.setString("id", String.valueOf(c.getId()));
		q.executeUpdate();

		tx.commit();
		session.close();

		c.setState(Constants.DELETED);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Client existClientId(String clientId) {
		Session session = this.sessionFactory.openSession();
		Query q = session
				.createQuery("from Client where generateID = (:clientID) and state != (:state)");
		q.setString("clientID", clientId);
		q.setString("state", Constants.DELETED);

		List<Client> clientList = (List<Client>) q.list();
		session.close();
		return clientList.size() == 1 ? clientList.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeTokens(Client client) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		try {
		Query q = session
		.createQuery("from Token where client = (:clientID)");
		System.out.println("*** CLIENT ID "+client.getId());
		q.setInteger("clientID", client.getId());
		List<Token> list = (List<Token>) q.list();
		for (Token t : list) {
		System.out.println("*** TOKEN " +t.getId());
		Query qDeleteRefresh = session
		.createQuery("delete from RefreshToken where token_id = (:id) ");
		qDeleteRefresh.setInteger("id", t.getId());
		qDeleteRefresh.executeUpdate();

		Query qDeleteToken = session
		.createQuery("delete from  Token where id = (:id) ");
		qDeleteToken.setInteger("id", t.getId());
		qDeleteToken.executeUpdate();

		}
		tx.commit();
		} catch (Exception e) {
		e.printStackTrace();
		tx.rollback();
		}
		session.close();
		}

}
