package cl.kvz.provida.oauth2.admin.dao;

import cl.kvz.provida.oauth2.admin.model.code.Code;

public interface ICodeDAO {
	public Code findLastByApp(int clientId);

	public void save(Code code);
}
