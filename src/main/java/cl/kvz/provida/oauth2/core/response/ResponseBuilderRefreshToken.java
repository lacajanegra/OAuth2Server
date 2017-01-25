package cl.kvz.provida.oauth2.core.response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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

public class ResponseBuilderRefreshToken extends ResponseBuilder {

	private static ITokenDAO tokenDao = (ITokenDAO) ServiceLocator
			.getInstance().getBean(ITokenDAO.class);

	@Override
	public Response buildResponse(HttpServletRequest request, Client client)
			throws OAuthSystemException, OAuthProblemException, URISyntaxException {

		OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
		
		Utils utils = new Utils();
		String client_id = "";
		try {
			Map<String, Object> jwt = utils.decodeTokenJWT(oauthRequest.getParam("code").toString());
			client_id = jwt.get("client_id").toString();
			System.out.println("desde token builder obtuve client: " + client_id);
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SignatureException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String jwt = utils.createTokenJWT(client_id);
		final String token = jwt;
		System.out.println("desde token builder genere token : " + client_id);
		
		OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());

		//final String token = oauthIssuerImpl.accessToken();
		final String refreshToken = oauthIssuerImpl.refreshToken();
		this.saveToken(client, token, refreshToken);
	
		OAuthResponse response = OAuthASResponse
				.tokenResponse(HttpServletResponse.SC_OK)
				.setTokenType(OAuth.OAUTH_BEARER_TOKEN)
				.setAccessToken(token)
				.setRefreshToken(refreshToken)
				.setExpiresIn("3600").buildJSONMessage();
		return Response.status(response.getResponseStatus())
				.entity(response.getBody()).build();
	
	}

	private void saveToken(Client client, String tokenGenerated, String refreshTokenGenerated)
			throws OAuthSystemException {
		Date date = new Date();
		Token token = new Token();
		token.setToken(tokenGenerated);
		token.setDateCreation(date);
		token.setClient(client);

		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setDateCreation(date);
		refreshToken.setRefreshToken(refreshTokenGenerated);

		token.getRefreshtokens().add(refreshToken);

		tokenDao.save(token);

	}

}
