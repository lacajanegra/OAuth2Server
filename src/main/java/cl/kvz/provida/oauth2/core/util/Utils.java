package cl.kvz.provida.oauth2.core.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;


public class Utils {
	
	
	
	/*
	 * Capitaliza una palabra
	 */
	public static String capitalize(final String line) {
		   return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}
	
	public static String formatRutToKN(String rut) {
		int zerosToConcat = 20 - rut.length();
		for(int i=0; i<zerosToConcat; i++){
			rut = "0".concat(rut);
		}
	   return rut;
}


	public String httpRequest(String URL) throws Exception{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try
	    {
//		HttpGet getRequest = new HttpGet("http://10.172.130.40:8080/ApiMQProvida/llamadaMQProvida?TRX=KN28&MAPA=KNMC28S&CLAIDEN=00000000000022568715&DIGIDEN=3&IPTELEF=");
		HttpGet getRequest = new HttpGet(URL);
		getRequest.addHeader("accept", "application/xml");
		HttpResponse response = httpClient.execute(getRequest);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) 
        {
            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
        }
		HttpEntity httpEntity = response.getEntity();
		String apiOutput = EntityUtils.toString(httpEntity);
//		System.out.println(apiOutput);
		return apiOutput;
	    }
	    finally
	    {
	        //Important: Close the connect
	        httpClient.getConnectionManager().shutdown();
	    }
	}
	
	
	public String parseXmlResponse(String parentNode, String childNode, String xml) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		StringBuilder xmlStringBuilder = new StringBuilder();
		xmlStringBuilder.append(xml);
		ByteArrayInputStream input =  new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
		Document doc = builder.parse(input);
		Element root = doc.getDocumentElement();


		NodeList nList = doc.getElementsByTagName(parentNode);
		
		 for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);

	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement = (Element) nNode;
	             
	               return (eElement
	                   .getElementsByTagName(childNode)
	                   .item(0)
	                   .getTextContent());
	            }else{
	            	return "none";
	            }
		 }
		return "none";
	}
	
	public String getClientIdFromCookieString(Map<String, javax.ws.rs.core.Cookie> cookies){
		String[] parts;
		String rut = "";
		for (Map.Entry<String, javax.ws.rs.core.Cookie> entry : cookies.entrySet())
		 {	
			 System.out.println(entry.getValue());
			 if(entry.getKey().equals(Constants.USER)){
				 
				 parts = entry.getValue().toString().split("=");
				 rut = parts[1].toString();
				 System.out.println("rut es " + rut);
				 return rut;
				
			 }
			 
		     
		 }
		return null;
	}
	
	public String getURIFromCookieString(Map<String, javax.ws.rs.core.Cookie> cookies){
		String[] parts;
		String uri = "";
		for (Map.Entry<String, javax.ws.rs.core.Cookie> entry : cookies.entrySet())
		 {	
			 System.out.println(entry.getValue());
			 if(entry.getKey().equals(Constants.URICOOKIE)){
				 
				 parts = entry.getValue().toString().split(Constants.URICOOKIE+"=");
				 uri = parts[1].toString();
				 System.out.println("uri es " + uri);
				 return uri;
				
			 }
			 
		     
		 }
		return null;
	}
	
	public String createTokenJWT(String client_id){
		 final String secret = "ProvidaSignCode";
		 final long iat = System.currentTimeMillis() / 1000l; // issued at claim 

		 final JWTSigner signer = new JWTSigner(secret);
		 final HashMap<String, Object> claims = new HashMap<String, Object>();
		 claims.put("client_id", client_id);
		 claims.put("iat", iat);
		 
		 final String jwt = signer.sign(claims);
		 return jwt;
	}
	public String createRefreshTokenJWT(String client_id){
		 final String secret = "ProvidaSignCode";
		 final long iat = System.currentTimeMillis() / 1000l; // issued at claim 

		 final JWTSigner signer = new JWTSigner(secret);
		 final HashMap<String, Object> claims = new HashMap<String, Object>();
		 claims.put("client_id", client_id);
		 claims.put("iat", iat);
		 claims.put("rt", "rt"); // refresh token
		 
		 final String jwt = signer.sign(claims);
		 return jwt;
	}
	
	public Map<String,Object> decodeTokenJWT(String jwt) throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, SignatureException, IOException{
		 try {
		     final JWTVerifier verifier = new JWTVerifier("ProvidaSignCode");
		     final Map<String,Object> claims= verifier.verify(jwt);
		     return claims;
		 } catch (JWTVerifyException e) {
		     // Invalid Token
			 return null;
		 }
	}
	
	

}
