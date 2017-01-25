/**
 * 
 */
package cl.kvz.provida.oauth2.core.endpoint;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

import cl.kvz.provida.oauth2.admin.configuration.ServiceLocator;
import cl.kvz.provida.oauth2.admin.dao.IGrantDAO;
import cl.kvz.provida.oauth2.admin.model.Grant;
import cl.kvz.provida.oauth2.core.pojo.GenericResponse;

/**
 * @author MCastillo
 *
 */
@Path("/grant")
public class GrantEndpoint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7593535937947588807L;
	
	private static final transient Logger LOGGER = Logger.getLogger( GrantEndpoint.class );
	private static IGrantDAO dao = (IGrantDAO) ServiceLocator.getInstance()
			.getBean(IGrantDAO.class);
	
	@GET
	@Path("/list")
	@Produces("application/json")
	public GenericResponse list(@Context HttpServletRequest request) {
		try {
			List<Grant> list = dao.list();
			
			return new GenericResponse(list);

		} catch (Exception e) {
			LOGGER.error(e);
			return new GenericResponse(99,e.getMessage());
		}
	}
}
