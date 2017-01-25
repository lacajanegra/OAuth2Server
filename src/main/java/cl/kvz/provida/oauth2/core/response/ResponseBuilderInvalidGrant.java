package cl.kvz.provida.oauth2.core.response;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import cl.kvz.provida.oauth2.admin.model.Client;
import cl.kvz.provida.oauth2.core.util.Constants;

public class ResponseBuilderInvalidGrant extends ResponseBuilder{

	@Override
	public Response buildResponse(HttpServletRequest request,Client client) throws OAuthSystemException, OAuthProblemException, URISyntaxException {
		
		return ResponseBuilder.buildInvalidResponse(
				OAuthError.TokenResponse.INVALID_GRANT,
				Constants.INVALID_GRANT_DESCRIPTION);
		
	}

}
