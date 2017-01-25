package cl.kvz.provida.oauth2.core.response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;

import cl.kvz.provida.oauth2.admin.model.Client;

public abstract class ResponseBuilder {
	
	
	public abstract Response buildResponse(HttpServletRequest request, Client client) throws OAuthSystemException, OAuthProblemException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, SignatureException, IOException;
	
	/*
	 * Construye respuesta de error en el Servidor Oauth
	 */
    public static Response buildInvalidResponse(String error, String description) throws OAuthSystemException {
        OAuthResponse response =
                OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                .setError(error)
                .setErrorDescription(description)
                .buildJSONMessage();
        return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
    }

	public Response buildResponse(HttpServletRequest request, Client client, String client_id)
			throws OAuthSystemException, OAuthProblemException, URISyntaxException {
		// TODO Auto-generated method stub
		return null;
	}
}

