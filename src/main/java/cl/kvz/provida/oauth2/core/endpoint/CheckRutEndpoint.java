package cl.kvz.provida.oauth2.core.endpoint;

import java.io.Serializable;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.XML;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;

import cl.kvz.provida.oauth2.core.pojo.FormUser;
import cl.kvz.provida.oauth2.core.pojo.GenericResponse;
import cl.kvz.provida.oauth2.core.util.Constants;
import cl.kvz.provida.oauth2.core.util.Utils;
import cl.kvz.provida.oauth2.core.util.Validator;

@Path("/checkRut")
public class CheckRutEndpoint implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final transient Logger LOGGER = Logger.getLogger( CheckRutEndpoint.class );
	 @GET
	    @Produces("text/html")
	    public Response CheckRutEndpoint(@Context HttpServletRequest request,  @Context HttpHeaders hh, @Context HttpRequestBase request2) throws Exception {
		 	System.out.println("constante tst es" + Constants.INVALID_CLIENT_DESCRIPTION);
		 	String rut = request.getParameter("rut");
		 	String tipoPer, Kn28State;
		 	System.out.println("rut a checkear es: "+ rut);
			Utils utils = new Utils();
			//se ejecuta trx pe27
			String xml = utils
					.httpRequest(Constants.LLAMADA_MQ +"?TRX=PE27&MAPA=PEM270S&OPCION=1&CLAIDEN="+ utils.
					formatRutToKN(rut.substring(0, rut.length() - 1))+"&TIPERSO=&CODIDEN=01&NOMBRE=&PRIAPE=&SEGAPE=&CLAVEPA=");
			
			
	         JSONObject pe27Json = XML.toJSONObject(xml);
	         
	        
	        	 
	         try{
		        	String response = pe27Json.getJSONObject("llamadaMQ").getJSONObject("Salida").getJSONObject("RESPUESTAS").getJSONObject("RESPUESTA").get("NUMCLIE").toString();
		        	System.out.println("respuesta de la revisión (numclie) de rut es: "+ response); // usuario existe
		        	 
		        	 //identificando tipo de persona desde trx pe27
			        try{
							tipoPer = pe27Json.getJSONObject("llamadaMQ").getJSONObject("Salida").getJSONObject("RESPUESTAS").getJSONObject("RESPUESTA").get("TIPOPER").toString();
							System.out.println("Tipo de persona es: "+ tipoPer); 
					}catch(Exception e){
							return Response.ok("{\"status\": \"error\",\"code\": \"99\",\"description\": \"Person type is not valid.\"}").type(MediaType.APPLICATION_JSON).build();
					}
			         
			         if(tipoPer.equals("15")){ //tipo persona= delegado, por lo tanto sigue flujo normal
			         		System.out.println("Usuario válido"); 
			        		return Response.ok("{\"status\": \"done\",\"code\": \"0\",\"description\": \"User exists.\"}").type(MediaType.APPLICATION_JSON).build();
			         }else{
			        	 	System.out.println("Consultando TRX kn28"); 
				        	String kn28xml = utils
										.httpRequest(Constants.LLAMADA_MQ +"?TRX=KN28&MAPA=KNMC28S&CLAIDEN="+ utils.
												formatRutToKN(rut.substring(0, rut.length() - 1))+"&DIGIDEN="+rut.substring(rut.length() - 1, rut.length())+"&IPTELEF=");
							JSONObject kn28Json = XML.toJSONObject(kn28xml);
							try{
									Kn28State = kn28Json.getJSONObject("llamadaMQ").getJSONObject("Salida").get("Estado").toString();
									if(Kn28State.equals("OK")){
										System.out.println("Estado es: " + Kn28State + ". Usuario es válido"); 
										return Response.ok("{\"status\": \"done\",\"code\": \"0\",\"description\": \"User exists.\"}").type(MediaType.APPLICATION_JSON).build();
									}else{
										System.out.println("Estado es: " + Kn28State + ". Usuario no es válido"); 
										return Response.ok("{\"status\": \"error\",\"code\": \"99\",\"description\": \"User does not exists.\"}").type(MediaType.APPLICATION_JSON).build();
									}
									
							}catch(Exception e){
									System.out.println("No se encuentra campo 'estado' en respuesta"); 
									return Response.ok("{\"status\": \"error\",\"code\": \"99\",\"description\": \"User does not exists.\"}").type(MediaType.APPLICATION_JSON).build();
							}
			         }
	        	 
	        	 
	        	 
	        	 //return Response.ok("{\"status\": \"done\",\"code\": \"0\",\"description\": \"User exists.\"}").type(MediaType.APPLICATION_JSON).build();
			}catch(Exception e){
					System.out.println("Usuario no existe en los registros.");
					return Response.ok("{\"status\": \"error\",\"code\": \"99\",\"description\": \"User does not exists.\"}").type(MediaType.APPLICATION_JSON).build();
				
			}
	         
	    }
}