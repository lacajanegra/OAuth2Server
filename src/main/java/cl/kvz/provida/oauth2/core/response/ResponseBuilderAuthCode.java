package cl.kvz.provida.oauth2.core.response;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;

import cl.kvz.provida.oauth2.admin.configuration.ServiceLocator;
import cl.kvz.provida.oauth2.admin.dao.ITokenDAO;
import cl.kvz.provida.oauth2.admin.model.Client;
import cl.kvz.provida.oauth2.admin.model.code.RefreshToken;
import cl.kvz.provida.oauth2.admin.model.code.Token;
import cl.kvz.provida.oauth2.core.util.Utils;

public class ResponseBuilderAuthCode extends ResponseBuilder {

	private static ITokenDAO tokenDao = (ITokenDAO) ServiceLocator
			.getInstance().getBean(ITokenDAO.class);

	@Override
	public Response buildResponse(HttpServletRequest request, Client client)
			throws OAuthSystemException, InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, SignatureException, IOException {
		OAuthTokenRequest oauthRequest = null;
		try {
			oauthRequest = new OAuthTokenRequest(request);
		} catch (OAuthProblemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Utils utils = new Utils();
		String client_id = "";

			Map<String, Object> jwt = utils.decodeTokenJWT(oauthRequest.getParam("code").toString());
			client_id = jwt.get("client_id").toString();
			System.out.println("desde token builder obtuve client: " + client_id);

			final String token = utils.createTokenJWT(client_id);
			final String refreshToken = utils.createRefreshTokenJWT(client_id);
			
		System.out.println("desde token builder genere token : " + token);
		
		
		OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());

		//final String token = oauthIssuerImpl.accessToken();
		//final String refreshToken = oauthIssuerImpl.refreshToken();
		this.saveToken(client, token,refreshToken);

		OAuthResponse response = OAuthASResponse
				.tokenResponse(HttpServletResponse.SC_OK)
				.setTokenType(OAuth.OAUTH_BEARER_TOKEN)
				.setAccessToken(token)
				.setRefreshToken(refreshToken)
				.setExpiresIn(String.valueOf(client.getTimeExpiration())).buildJSONMessage();
		return Response.status(response.getResponseStatus())
				.entity(response.getBody()).build();
	}

	private void saveToken(Client client, String t, String rt)
			throws OAuthSystemException {
		Date date = new Date();
		Token token = new Token();
		token.setToken(t);
		token.setDateCreation(date);
		token.setClient(client);

		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setDateCreation(date);
		refreshToken.setRefreshToken(rt);

		token.getRefreshtokens().add(refreshToken);

		tokenDao.save(token);

	}

}