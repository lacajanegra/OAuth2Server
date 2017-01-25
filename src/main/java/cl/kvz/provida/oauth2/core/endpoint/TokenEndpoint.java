/**
 * 
 */
package cl.kvz.provida.oauth2.core.endpoint;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;

import cl.kvz.provida.oauth2.admin.configuration.ServiceLocator;
import cl.kvz.provida.oauth2.admin.dao.IClientDAO;
import cl.kvz.provida.oauth2.admin.model.Client;
import cl.kvz.provida.oauth2.core.exception.OauthException;
import cl.kvz.provida.oauth2.core.response.ResponseBuilder;
import cl.kvz.provida.oauth2.core.util.Constants;
import cl.kvz.provida.oauth2.core.util.Reflection;
import cl.kvz.provida.oauth2.core.util.Utils;
import cl.kvz.provida.oauth2.core.util.Validator;


/**
 * @author MCastillo
 *
 */
@Path("/token")
public class TokenEndpoint implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7388344134873720964L;
	private static final transient Logger LOGGER = Logger.getLogger( TokenEndpoint.class );
	
	private static IClientDAO clientDao = (IClientDAO) ServiceLocator.getInstance()
			.getBean(IClientDAO.class);
	

	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public Response authorize(@Context HttpServletRequest request)
			throws OAuthSystemException, OauthException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, SignatureException, IOException {
		try {
			
			String responseType = "";
			OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);
			
			
			Client client = clientDao.existClientId(oauthRequest.getClientId());
			
			if (!Validator.checkClientId(client))
				responseType = Constants.INVALID_CLIENT;
			
		
			
			// check if client_secret is valid
			// if (!checkClientSecret(oauthRequest.getClientSecret())) {
			// return buildInvalidClientSecretResponse();
			// }

			
			if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(
					GrantType.AUTHORIZATION_CODE.toString())) {
				responseType = Constants.AUTH_CODE;
				if (!Validator.checkAuthCode(client,oauthRequest.getParam(OAuth.OAUTH_CODE))) {
					return buildBadAuthCodeResponse();
				}
			} 
			else if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(
					GrantType.REFRESH_TOKEN.toString())) {
				responseType = Constants.REFRESH;
				if(!Validator.checkRefreshToken(client,oauthRequest.getParam(OAuth.OAUTH_REFRESH_TOKEN)))
					return buildBadAuthCodeResponse();
			}
			/**
			 * Mapea el objeto a un respuesta segun el request
			 */
			ResponseBuilder response = Reflection
					.getResponseOauthObject(responseType);
			return response.buildResponse(request,client);
			
			

		} catch (OAuthProblemException e) {
			LOGGER.error(e);
			OAuthResponse res = OAuthASResponse
					.errorResponse(HttpServletResponse.SC_BAD_REQUEST).error(e)
					.buildJSONMessage();
			return Response.status(res.getResponseStatus())
					.entity(res.getBody()).build();
		}
	}

	private Response buildBadAuthCodeResponse() throws OAuthSystemException {
		OAuthResponse response = OAuthASResponse
				.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
				.setError(OAuthError.TokenResponse.INVALID_GRANT)
				.setErrorDescription("invalid authorization code")
				.buildJSONMessage();
		return Response.status(response.getResponseStatus())
				.entity(response.getBody()).build();
	}

}