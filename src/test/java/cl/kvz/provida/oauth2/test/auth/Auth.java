package cl.kvz.provida.oauth2.test.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

public class Auth {
	
	public static String CLIENT_ID = "oauth2test";
    public static String CLIENT_SECRET = "oauth2clientsecret";
    public static String AUTHORIZATION_CODE = "oauth2authcode";
    public static String USERNAME = "user";
    public static String PASSWORD = "pass";
    public static String RESOURCE_SERVER_NAME = "resource";
    public static final String ACCESS_TOKEN_VALID = "access_token_valid";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_HEADER_OAUTH2 = "Bearer " + ACCESS_TOKEN_VALID;
    private Client client = JerseyClientBuilder.newClient();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		try {
            Response response = makeAuthCodeRequest();
            assertEquals(Status.OK.getStatusCode(), response.getStatus());

            String authCode = getAuthCode(response);
            assertNotNull(authCode);
        } catch (OAuthSystemException | URISyntaxException | JSONException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	private Response makeAuthCodeRequest() throws OAuthSystemException, URISyntaxException {
        OAuthClientRequest request = OAuthClientRequest
                .authorizationLocation("oauth2/auth")
                .setClientId(CLIENT_ID)
                .setRedirectURI("oauth/redirect")
                .setResponseType(ResponseType.CODE.toString())
                .setState("state")
                .buildQueryMessage();
        WebTarget target = client.target(new URI(request.getLocationUri()));
        Response response = target.request(MediaType.TEXT_HTML).get();
        return response;
    }
	
	private String getAuthCode(Response response) throws JSONException {
        JSONObject obj = new JSONObject(response.readEntity(String.class));
        JSONObject qp = obj.getJSONObject("queryParameters");
        String authCode = null;
        if (qp != null) {
            authCode = qp.getString("code");
        }

        return authCode;
    }
}
