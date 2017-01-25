package cl.kvz.provida.oauth2.admin.dao;

import cl.kvz.provida.oauth2.admin.model.code.RefreshToken;
import cl.kvz.provida.oauth2.admin.model.code.Token;

public interface ITokenDAO {

	public void save(Token t);

	public Token verifyToken(String bearer);

	public Token findLastByApp(int clientId);

	public RefreshToken findLastByToken(int tokenId);

}
