package cl.kvz.provida.oauth2.core.response;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;

import com.auth0.jwt.JWTSigner;

import cl.kvz.provida.oauth2.admin.configuration.ServiceLocator;
import cl.kvz.provida.oauth2.admin.dao.ICodeDAO;
import cl.kvz.provida.oauth2.admin.model.Client;
import cl.kvz.provida.oauth2.admin.model.code.Code;
import cl.kvz.provida.oauth2.core.util.Utils;

public class ResponseBuilderCode extends ResponseBuilder{

	private static ICodeDAO codeDao = (ICodeDAO) ServiceLocator.getInstance()
			.getBean(ICodeDAO.class);
	
	@Override
	public Response buildResponse(HttpServletRequest request, Client client, String client_id) throws OAuthSystemException, OAuthProblemException, URISyntaxException {
		System.out.println("desde builder code generando code para " + client_id);
		OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(
				new MD5Generator());
		
		OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
		
		OAuthASResponse.OAuthAuthorizationResponseBuilder builder = OAuthASResponse
				.authorizationResponse(request,
						HttpServletResponse.SC_FOUND);
		
		Utils utils = new Utils();
		String jwt = utils.createTokenJWT(client_id);
		 final String authorizationCode = jwt;
		 System.out.println("code generado es " + authorizationCode);
//		final String authorizationCode = oauthIssuerImpl.authorizationCode();
		
		this.saveCode(client,authorizationCode);
		
		builder.setCode(authorizationCode);
		
		builder.setScope( oauthRequest
				.getParam(OAuth.OAUTH_STATE));
		String redirectURI = oauthRequest
				.getParam(OAuth.OAUTH_REDIRECT_URI);
		final OAuthResponse response = builder.location(redirectURI)
				.buildQueryMessage();
		URI url = new URI(response.getLocationUri());
		return Response.status(response.getResponseStatus()).location(url)
				.build();
	}

	private void saveCode(Client client,String authorizationCode) {
		
		Code code = new Code();
		code.setClient(client);
		code.setCode(authorizationCode);
		code.setDateCreation(new Date());
		
		codeDao.save(code);
		
	}

	@Override
	public Response buildResponse(HttpServletRequest request, Client client)
			throws OAuthSystemException, OAuthProblemException, URISyntaxException {
		// TODO Auto-generated method stub
		return null;
	}

}
