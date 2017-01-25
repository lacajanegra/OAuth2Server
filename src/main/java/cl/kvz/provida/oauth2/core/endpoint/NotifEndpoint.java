package cl.kvz.provida.oauth2.core.endpoint;

import java.io.Serializable;
import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import cl.kvz.provida.oauth2.core.pojo.FormUser;
import cl.kvz.provida.oauth2.core.pojo.GenericResponse;
import cl.kvz.provida.oauth2.core.util.Constants;
import cl.kvz.provida.oauth2.core.util.Validator;

@Path("/notif")
public class NotifEndpoint implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final transient Logger LOGGER = Logger.getLogger( NotifEndpoint.class );
	 @GET
	    @Produces("text/html")
	    public Response serveCookie(@Context HttpServletRequest request) {
		 try {		
			 
			 int timeout = Integer.parseInt(request.getParameter("timeout"));
			 
				
				
				
			 String client_id = request.getParameter("client_id");
			 LOGGER.info("timeout " + timeout + "client_id " + client_id);
			 //NewCookie cookie = new NewCookie(Constants.USER,client_id, "/",Constants.DEVDOMAIN, "USER LOGGED PROVIDA",timeout, false);
			 NewCookie cookie = new NewCookie(Constants.USER,client_id, "/",Constants.DEVDOMAIN, "USER LOGGED PROVIDA",timeout, false);
			 //NewCookie cookie2 = new NewCookie(Constants.USER,client_id, "/",".prov-inf-dev.provida.cl", "USER LOGGED PROVIDA",timeout, false);
			 //return Response.ok("{\"status\": \"done\",\"code\": \"200\",\"description\": \"The cookie has been saved.\"}").type(MediaType.APPLICATION_JSON).cookie(cookie).build();
			 return Response.ok("{\"status\": \"done\",\"code\": \"200\",\"description\": \"The cookie has been saved.\"}").type(MediaType.APPLICATION_JSON).cookie(cookie).build();
		 } catch (Exception e) {	
				LOGGER.error(e);
				return Response.ok("{\"status\": \"error\",\"code\": \"400\"}").type(MediaType.APPLICATION_JSON).build();
			}
	    }
}
 