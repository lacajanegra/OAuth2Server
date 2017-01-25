/**
 * 
 */
package cl.kvz.provida.oauth2.core.endpoint;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import cl.kvz.provida.oauth2.admin.configuration.ServiceLocator;
import cl.kvz.provida.oauth2.admin.dao.IUserDAO;
import cl.kvz.provida.oauth2.admin.model.User;
import cl.kvz.provida.oauth2.core.pojo.GenericResponse;

/**
 * @author MCastillo
 *
 */
@Path("/user")
public class UserEndpoint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1637328572147268419L;
	
	private static final transient Logger LOGGER = Logger.getLogger( UserEndpoint.class );
	private static IUserDAO dao = (IUserDAO) ServiceLocator.getInstance()
			.getBean(IUserDAO.class);

	@POST
	@Path("/login")
	@Consumes("application/json")
	@Produces("application/json")
	public GenericResponse list(@Context HttpServletRequest request,User u) {
		try {
						
			User user = dao.findById(u);
			
			return new GenericResponse(user);

		} catch (Exception e) {
			LOGGER.error(e);
			return new GenericResponse(99,e.getMessage());
		}
	}
	
	@GET
	@Path("/list")
	public Response list(@Context HttpServletRequest request) {
		try {
			List<User> list = dao.list();

			return Response.status(200).entity(list.get(0).getName()).build();

		} catch (Exception e) {
			LOGGER.error(e);
			return Response.status(200).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/save")
	public Response save(@Context HttpServletRequest request, User u) {
		try {
			List<User> list = dao.list();

			return Response.status(200).entity(list.get(0).getDateCreation())
					.build();

		} catch (Exception e) {
			LOGGER.error(e);
			return Response.status(200).entity(e.getMessage()).build();
		}
	}
}
