package cl.kvz.provida.oauth2.core.response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import cl.kvz.provida.oauth2.admin.model.Client;
import cl.kvz.provida.oauth2.core.util.Constants;

public class ResponseBuilderInvalidClient extends ResponseBuilder{

	@Override
	public Response buildResponse(HttpServletRequest request, Client client, String client_id) throws OAuthSystemException, OAuthProblemException, URISyntaxException {
		
		return ResponseBuilder.buildInvalidResponse(
				OAuthError.TokenResponse.INVALID_CLIENT,
				Constants.INVALID_CLIENT_DESCRIPTION);
		
	}

	@Override
	public Response buildResponse(HttpServletRequest request, Client client)
			throws OAuthSystemException, OAuthProblemException, URISyntaxException, InvalidKeyException,
			NoSuchAlgorithmException, IllegalStateException, SignatureException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
