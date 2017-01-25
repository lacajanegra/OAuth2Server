package cl.kvz.provida.oauth2.admin.dao;

import java.util.List;

import cl.kvz.provida.oauth2.admin.model.Client;

public interface IClientDAO {

	public void save(Client u);

	public List<Client> list();

	public void update(Client c);

	public void delete(Client c);

	public Client existClientId(String clientId);

	public void removeTokens(Client client);
}
