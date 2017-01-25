/**
 * 
 */
package cl.kvz.provida.oauth2.core.endpoint;

import java.io.Serializable;
import java.net.URI;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.apache.log4j.Logger;

import cl.kvz.provida.oauth2.core.pojo.FormUser;
import cl.kvz.provida.oauth2.core.pojo.GenericResponse;
import cl.kvz.provida.oauth2.core.util.Constants;
import cl.kvz.provida.oauth2.core.util.Utils;
import cl.kvz.provida.oauth2.core.util.Validator;

/**
 * @author MCastillo
 *
 */
@Path("/clientLogin")
public class ClientLogin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4118411063077030039L;
	
	private static final transient Logger LOGGER = Logger.getLogger( ClientLogin.class );


	@POST
	@Consumes("application/json")
	public GenericResponse authorize(@Context HttpServletRequest request,FormUser user, @Context HttpHeaders hh) {
		try {	
			LOGGER.info("Validando trx LDAP");
			if(!Validator.checkCientCredentials(user))
			//if(!Validator.checkCientCredentials(new FormUser("35554998","1111")))
			return new GenericResponse(99,Constants.ERROR_LDAP);
			LOGGER.info("Validando trx KN");
			if(!Validator.checkCientCredentialsKN(user))
			return new GenericResponse(98,Constants.ERROR_KN);	
			
			Utils utils = new Utils();
			Map<String, javax.ws.rs.core.Cookie> cookies = hh.getCookies();
			String uri = utils.getURIFromCookieString(cookies);
			
			request.getSession().setAttribute(Constants.USER, user.getUser());
		
			return new GenericResponse(uri);			

		} catch (Exception e) {	
			LOGGER.error(e);
			return new GenericResponse(99,e.getMessage());
		}
	}
}
