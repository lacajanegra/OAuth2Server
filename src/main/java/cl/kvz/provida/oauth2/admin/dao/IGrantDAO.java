package cl.kvz.provida.oauth2.admin.dao;

import java.util.List;

import cl.kvz.provida.oauth2.admin.model.Grant;

public interface IGrantDAO {

	public void save(Grant u);

	public List<Grant> list();
}
