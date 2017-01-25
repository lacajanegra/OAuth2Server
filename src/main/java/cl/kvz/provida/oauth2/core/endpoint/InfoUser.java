	 /**
 * 
 */
package cl.kvz.provida.oauth2.core.endpoint;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.thoughtworks.xstream.XStream;

import cl.kvz.provida.oauth2.admin.model.Client;
import cl.kvz.provida.oauth2.admin.model.UserInfo;
import cl.kvz.provida.oauth2.core.pojo.GenericResponse;
import cl.kvz.provida.oauth2.core.util.Constants;
import cl.kvz.provida.oauth2.core.util.Utils;
import cl.kvz.provida.oauth2.core.util.Validator;

/**
 * @author MCastillo
 *
 */

@Path("/infoUser")
public class InfoUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4118411063077030039L;
	
	private static final transient Logger LOGGER = Logger.getLogger( InfoUser.class );


	@GET
	@Produces("application/json")
	public GenericResponse authorize(@Context HttpServletRequest request) {
		try {
			final String authorization = request.getHeader("Authorization");
			if (authorization != null && authorization.startsWith("Bearer")) {
			String bearer = authorization.split(" ")[1];
			if (!Validator.checkToken(bearer))
			return new GenericResponse(99, Constants.TOKEN_INACTIVE);
			LOGGER.info("Resultado validación Token: " + Validator.checkToken(bearer));
			if (!Validator.checkToken(bearer))
				return new GenericResponse("{\"codResponse\": \"99\",\"glosaResponse\": \"" + Constants.TOKEN_INACTIVE + "\"}");
			
			
			
			//System.out.println("ES" + Validator.checkToken(bearer));
			UserInfo user = new UserInfo();
			Utils utils = new Utils();
			String rut, numclie = "";
			//decode jwt to obtain client_id (rut)
			Map<String, Object> jwt = utils.decodeTokenJWT(bearer);
			rut = jwt.get("client_id").toString();

			 XStream xStream = new XStream();
			if(rut == null)
			return new GenericResponse(99, "No se ha encontrado un rut correcto");
			
			String xml = utils
					.httpRequest(Constants.LLAMADA_MQ +"?TRX=PE27&MAPA=PEM270S&OPCION=1&CLAIDEN="+ utils.
					formatRutToKN(rut.substring(0, rut.length() - 1))+"&TIPERSO=&CODIDEN=01&NOMBRE=&PRIAPE=&SEGAPE=&CLAVEPA=");
			
			try {
	            JSONObject xmlJSONObj = XML.toJSONObject(xml);
	            String jsonPrettyPrintString = xmlJSONObj.toString();
	            System.out.println(jsonPrettyPrintString);
	            user.setName(xmlJSONObj.getJSONObject("llamadaMQ").getJSONObject("Salida").getJSONObject("RESPUESTAS").getJSONObject("RESPUESTA").get("NOMCOMP").toString());
	            numclie = xmlJSONObj.getJSONObject("llamadaMQ").getJSONObject("Salida").getJSONObject("RESPUESTAS").getJSONObject("RESPUESTA").get("NUMCLIE").toString();
	        } catch (JSONException je) {
	        	return new GenericResponse(99, "Error al buscar usuario.");
	        }
			
			xml = utils
					.httpRequest(Constants.LLAMADA_MQ +"?TRX=KNZ1&MAPA=KNMCZ12&OPCION=P&CONTACT=&NRODOCU=&DV=&NUMCLI="+ numclie +
							"&TIPSERV=&CANAL=&ESTADO=&AGENCIA=&IDINFIN=");
			try {
	            JSONObject xmlJSONObj = XML.toJSONObject(xml);
	            String jsonPrettyPrintString = xmlJSONObj.toString();
	            user.setMail(xmlJSONObj.getJSONObject("llamadaMQ").getJSONObject("Salida").getJSONObject("RESPUESTAS").getJSONObject("RESPUESTA").get("EMAIL").toString());
	            user.setInfinityId(xmlJSONObj.getJSONObject("llamadaMQ").getJSONObject("Salida").getJSONObject("RESPUESTAS").getJSONObject("RESPUESTA").get("REFEREN").toString());
	        } catch (JSONException je) {
	        	return new GenericResponse(99, "Error al buscar usuario.");
	        }
			
			
			System.out.println("El usuario es " + user.getName());
			return new GenericResponse(user);
                                                                                                                                                                                                                                                                                                                                
		}
		return new GenericResponse(99, "Metodo de Autorizacion invalido");
		} catch (Exception e) {
		LOGGER.error(e);
		return new GenericResponse(99, e.getMessage());
		}
		}

	
}


