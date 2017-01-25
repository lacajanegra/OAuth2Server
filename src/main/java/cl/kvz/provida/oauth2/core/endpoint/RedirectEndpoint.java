package cl.kvz.provida.oauth2.core.endpoint;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;


import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author MCastillo
 * 
 */
@Path("/redirect")
public class RedirectEndpoint implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 340511743558198769L;
	
	private static final transient Logger LOGGER = Logger.getLogger( RedirectEndpoint.class );
	
	@Context
    HttpHeaders httpHeaders;
    @Context
    UriInfo uriInfo;
    
    

    @GET
    public String redirect() {
        JSONObject object = new JSONObject();
        JSONObject headers = new JSONObject(); 
        JSONObject qp = new JSONObject();
        String json = "error!";
        try {
            for (Map.Entry<String, List<String>> entry : httpHeaders.getRequestHeaders().entrySet()) {
                headers.put(entry.getKey(), entry.getValue().get(0));
            }
            object.put("headers", headers);
            for (Map.Entry<String, List<String>> entry : uriInfo.getQueryParameters().entrySet()) {
                qp.put(entry.getKey(), entry.getValue().get(0));
            }
            object.put("queryParameters", qp);
            json = object.toString(4);
        } catch (JSONException ex) {
            LOGGER.error(ex);
        }
        return json;
    }
}
