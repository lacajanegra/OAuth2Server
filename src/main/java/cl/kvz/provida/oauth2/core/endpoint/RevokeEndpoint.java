/**
 * 
 */
package cl.kvz.provida.oauth2.core.endpoint;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

import cl.kvz.provida.oauth2.admin.configuration.ServiceLocator;
import cl.kvz.provida.oauth2.admin.dao.IClientDAO;
import cl.kvz.provida.oauth2.admin.model.Client;
import cl.kvz.provida.oauth2.core.pojo.GenericResponse;

/**
 * @author MCastillo
 *
 */

@Path("/revoke")
public class RevokeEndpoint implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4118411063077030039L;
	
	private static final transient Logger LOGGER = Logger.getLogger( RevokeEndpoint.class );

	private static IClientDAO clientDao = (IClientDAO) ServiceLocator
			.getInstance().getBean(IClientDAO.class);

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public GenericResponse authorize(@Context HttpServletRequest request, Client client) {
		try {
			//rovakar todos los tokens
			clientDao.removeTokens(client);
			
			final HttpSession session = request.getSession();
			if(session!=null) session.invalidate();
			return new GenericResponse();			

		} catch (Exception e) {
			LOGGER.error(e);
			return new GenericResponse(99,e.getMessage());
		}
	}
}