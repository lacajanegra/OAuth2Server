/**
 * 
 */
package cl.kvz.provida.oauth2.core.endpoint;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;

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
@Path("/auth")
public class AuthEndpoint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4118411063077030039L;

	private static final transient Logger LOGGER = Logger
			.getLogger(AuthEndpoint.class);

	private static IClientDAO clientDao = (IClientDAO) ServiceLocator.getInstance()
			.getBean(IClientDAO.class);
	
	@Context
	private UriInfo uriInfo;

	@GET
	public Response authorize(@Context HttpServletRequest request,  @Context HttpHeaders hh)
			throws URISyntaxException, OAuthSystemException, OauthException {
		try {
//			//DETECTANDO FLUJO INFINITY, SI SE DETECTA SE DESPLIEGA VENTANA ACCOUNT ESPECIAL, SINO VENTANA GENERICA
//			try{
//				String flag = request.getParameter("flag").toString();
//				LOGGER.info("Identifiqué una flag: " + flag);
//			}catch(Exception e){
//			}
			

				
			final HttpSession session = request.getSession();
			Utils utils = new Utils();
			Map<String, javax.ws.rs.core.Cookie> cookies = hh.getCookies();
			String rut = utils.getClientIdFromCookieString(cookies);
//			if (session.getAttribute(Constants.USER) == null) {
			if (rut == null || rut == "") {
				LOGGER.info("--- No existe usuario autenticado.");
				//session.setAttribute(Constants.AUTH_CODE_URI, uriInfo.getRequestUri());
				LOGGER.info(uriInfo.getRequestUri().toString());
				String uriBeforeRedirect = uriInfo.getRequestUri().toString();
				URI location = new URI(Constants.LOGIN_CLIENT);
				NewCookie cookie = new NewCookie(Constants.URICOOKIE,uriBeforeRedirect, "/",Constants.DEVDOMAIN, "Uri Before redirect PROVIDA",500, false);
				return Response.temporaryRedirect(location).cookie(cookie).build();
			}
			
			OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
			LOGGER.info(request);
			String responseType = oauthRequest
					.getParam(OAuth.OAUTH_RESPONSE_TYPE);

			Client client = clientDao.existClientId(oauthRequest.getClientId());
			
			if (!Validator.checkClientId(client))
				responseType = Constants.INVALID_CLIENT;
			
			if(oauthRequest.getRedirectURI() == null || oauthRequest.getRedirectURI().length() == 0){
				responseType = Constants.INVALID_GRANT;
				LOGGER.info("uri error");
			}
				
			
//			if (!Validator.checkCallback(client,oauthRequest.getRedirectURI()))
//				responseType = Constants.INVALID_GRANT;

			/**
			 * Mapea el objeto a un respuesta segun el request
			 */
			ResponseBuilder response = Reflection
					.getResponseOauthObject(responseType);
			String client_id = rut; //obtengo el usuario para luego generar un codigo que contenga el client_id (rut)
			return response.buildResponse(request,client, client_id);

		} catch (OAuthProblemException e) {
			LOGGER.error(e);
			final Response.ResponseBuilder responseBuilder = Response
					.status(HttpServletResponse.SC_FOUND);
			
//			String redirectUri = e.getRedirectUri();

			final OAuthResponse response = OAuthASResponse
					.errorResponse(HttpServletResponse.SC_FOUND).error(e)
					.buildQueryMessage();
			final URI location = new URI(response.getLocationUri());
			return responseBuilder.location(location).build();
		}
	}

}
