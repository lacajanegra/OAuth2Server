package cl.kvz.provida.oauth2.admin.dao;

import java.util.List;

import cl.kvz.provida.oauth2.admin.model.User;

public interface IUserDAO {

	public void save(User u);

	public List<User> list();

	public User findById(User u);
}
