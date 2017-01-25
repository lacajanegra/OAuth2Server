/**
 * 
 */
package cl.kvz.provida.oauth2.core.endpoint;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import cl.kvz.provida.oauth2.admin.security.Security;
import cl.kvz.provida.oauth2.core.pojo.GenericResponse;
import cl.kvz.provida.oauth2.core.servlet.InitApp;

/**
 * @author MCastillo
 *
 */
@Path("/client")
public class ClientEndpoint implements Serializable{


	private static final long serialVersionUID = -2353267619713402436L;
	
	
	private static final transient Logger LOGGER = Logger.getLogger( InitApp.class );
	private static IClientDAO dao = (IClientDAO) ServiceLocator.getInstance()
			.getBean(IClientDAO.class);

	@GET
	@Path("/list")
	@Produces("application/json")
	public GenericResponse list(@Context HttpServletRequest request) {
		try {

			LOGGER.info("************************************************************************");
			LOGGER.info("Consulta Lista de Aplicaciones Cliente");
			List<Client> list = dao.list();

			return new GenericResponse(list);

		} catch (Exception e) {
			LOGGER.error(e);
			return new GenericResponse(99, e.getMessage());
		}
	}

	@POST
	@Path("/save")
	@Consumes("application/json")
	@Produces("application/json")
	public GenericResponse save(@Context HttpServletRequest request, Client c) {
		try {
			c.setGenerateID(Security.encrypt(c.getName()+"GenerateID"));
			c.setClientSecret(Security.encrypt(c.getName()+"ClientSecret"));
			c.setDateCreation(new Date());

			dao.save(c);

			return new GenericResponse(c);

		} catch (Exception e) {
			LOGGER.error(e);
			return new GenericResponse(99, e.getMessage());
		}
	}

	@POST
	@Path("/update")
	@Consumes("application/json")
	@Produces("application/json")
	public GenericResponse edit(@Context HttpServletRequest request, Client c) {
		try {
			c.setGenerateID(Security.encrypt(c.getName()));
			c.setClientSecret(Security.encrypt(c.getName()));

			dao.update(c);

			return new GenericResponse(c);

		} catch (Exception e) {
			LOGGER.error(e);
			return new GenericResponse(99, e.getMessage());
		}
	}

	@POST
	@Path("/delete")
	@Consumes("application/json")
	@Produces("application/json")
	public GenericResponse delete(@Context HttpServletRequest request, Client c) {
		try {

			dao.delete(c);

			return new GenericResponse(c);

		} catch (Exception e) {
			LOGGER.error(e);
			return new GenericResponse(99, e.getMessage());
		}
	}
}
