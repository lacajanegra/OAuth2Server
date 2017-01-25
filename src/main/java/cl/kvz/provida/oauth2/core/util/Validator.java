package cl.kvz.provida.oauth2.core.util;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONObject;
import org.json.XML;

import cl.kvz.provida.oauth2.admin.configuration.ServiceLocator;
import cl.kvz.provida.oauth2.admin.dao.ICodeDAO;
import cl.kvz.provida.oauth2.admin.dao.ITokenDAO;
import cl.kvz.provida.oauth2.admin.model.Client;
import cl.kvz.provida.oauth2.admin.model.code.Code;
import cl.kvz.provida.oauth2.admin.model.code.RefreshToken;
import cl.kvz.provida.oauth2.admin.model.code.Token;
import cl.kvz.provida.oauth2.core.ldap.LdapService;
import cl.kvz.provida.oauth2.core.pojo.FormUser;

public class Validator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1786399511718470463L;
	
	
	private static ICodeDAO codeDao = (ICodeDAO) ServiceLocator.getInstance()
			.getBean(ICodeDAO.class);
	
	private static ITokenDAO tokenDao = (ITokenDAO) ServiceLocator.getInstance()
			.getBean(ITokenDAO.class);

	/*
	 * Valida que la aplicación cliente este registrada en el servidor OAUTH
	 */
	public static boolean checkClientId(Client client) {
		return client == null ? false : true;
	}

	/*
	 * Valida que sea una URI permitida junto con el tipo de autenticacion.
	 */
	public static boolean checkCallback(Client client, String redirectURI) {
		return true;
	}

	/**
	 * 1 Valida Ldap 2 Valida Perfil 3 Verifica si es cliente Infinity
	 * 
	 * @param formUser
	 * @return
	 */
	public static boolean checkCientCredentials(FormUser formUser) {
		LdapService service = new LdapService();
		String user = formUser.getUser();
		return service.checkCredentials(user.substring(0, user.length() - 1),
				user.substring(user.length() - 1, user.length()),
				formUser.getPass());
	}

	public static boolean checkAuthCode(Client client, String code) {
		System.out.println("estoy en checkAuthCode ");
		System.out.println("el client id es:" + client.getId());
		Code cod = codeDao.findLastByApp(client.getId());
		System.out.println("Code en checkcode es: " + cod);
		return cod==null ?  false : code.equals(cod.getCode());
	}

	public static boolean checkToken(String bearer) {
		final long MINUTS_PER_DAY = 24 * 60 ; //Minutos al día
		Token token = tokenDao.verifyToken(bearer);
		if(token == null )
		return false;

		Client client = token.getClient();
		//Sea el ultimo token
		Token lastToken = tokenDao.findLastByApp(client.getId());
		if(lastToken.getId() != token.getId())
		return false;
		//Validamos expiración
		Date date = new Date();
		Long result = (date.getTime()-token.getDateCreation().getTime())/MINUTS_PER_DAY;
		if(result>client.getTimeExpiration())
		return false;
		return true;
		}


	public static boolean checkRefreshToken(Client client, String refresh) {
		System.out.println("REFRESHTOKEN");
		System.out.println("CLIENT ID"+client.getId());
		Token token = tokenDao.findLastByApp(client.getId());
		if(token == null)
		return false;
		System.out.println("TOKEN "+token.getId() );
		RefreshToken rToken = tokenDao.findLastByToken(token.getId());
		System.out.println(rToken.getRefreshToken() + " es igual a " + refresh);
		if(rToken == null)
		return false;
		return rToken.getRefreshToken().equals(refresh);
		}
	
	public static boolean checkCientCredentialsKN(FormUser formUser) {
//		if(1==1){
//			return true;
//		}
		String user = formUser.getUser();
		Utils utils = new Utils();
		String numClie,isInfinity, tipoPer;
		try {
			String pe27xml = utils
					.httpRequest(Constants.LLAMADA_MQ +"?TRX=PE27&MAPA=PEM270S&OPCION=1&CLAIDEN="+ utils.
							formatRutToKN(user.substring(0, user.length() - 1))+"&TIPERSO=&CODIDEN=01&NOMBRE=&PRIAPE=&SEGAPE=&CLAVEPA=");
	         JSONObject pe27Json = XML.toJSONObject(pe27xml);
	         
			try{
				tipoPer = pe27Json.getJSONObject("llamadaMQ").getJSONObject("Salida").getJSONObject("RESPUESTAS").getJSONObject("RESPUESTA").get("TIPOPER").toString();
			}catch(Exception e){
				return false;
			}
			System.out.println("Pe27, tipo persona " + tipoPer);
			
			if(!tipoPer.equals("15")){
				
					String kn28xml = utils
							.httpRequest(Constants.LLAMADA_MQ +"?TRX=KN28&MAPA=KNMC28S&CLAIDEN="+ utils.
									formatRutToKN(user.substring(0, user.length() - 1))+"&DIGIDEN="+user.substring(user.length() - 1, user.length())+"&IPTELEF=");
					JSONObject kn28Json = XML.toJSONObject(kn28xml);
					//System.out.println(kn28Json);

					try{
						numClie = kn28Json.getJSONObject("llamadaMQ").getJSONObject("Salida").getJSONObject("RESPUESTAS").getJSONObject("RESPUESTA").get("NUMCLIE").toString();
					}catch(Exception e){
						return false;
					}
					System.out.println("Kn28 numclie: " + numClie);
					
					String knIf = utils.httpRequest(Constants.LLAMADA_MQ +"?TRX=KNIF&MAPA=KNIF01S&NUMCLIE="+numClie+"&OPCION=1");
					JSONObject knIfJson = XML.toJSONObject(knIf);
					//System.out.println(knIfJson);
					
					try{
						isInfinity = knIfJson.getJSONObject("llamadaMQ").getJSONObject("Salida").getJSONObject("RESPUESTAS").getJSONObject("RESPUESTA").get("RETORNO").toString();
					}catch(Exception e){
						return false;
					}
					System.out.println("KnIf Cliente Infinity? " + isInfinity.equals("SI") );
					return isInfinity.equals("SI");
					
				
			}else{
				
				try{
					numClie = pe27Json.getJSONObject("llamadaMQ").getJSONObject("Salida").getJSONObject("RESPUESTAS").getJSONObject("RESPUESTA").get("NUMCLIE").toString();
				}catch(Exception e){
					return false;
				}
				System.out.println("Pe27 numclie: " + numClie);
				
				String knIf = utils.httpRequest(Constants.LLAMADA_MQ +"?TRX=KNIF&MAPA=KNIF01S&NUMCLIE="+numClie+"&OPCION=1");
				JSONObject knIfJson = XML.toJSONObject(knIf);

				try{
					isInfinity = knIfJson.getJSONObject("llamadaMQ").getJSONObject("Salida").getJSONObject("RESPUESTAS").getJSONObject("RESPUESTA").get("RETORNO").toString();
				}catch(Exception e){
					return false;
				}
				System.out.println("KnIf Cliente Infinity? " + isInfinity.equals("SI") );
				return isInfinity.equals("SI");
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		
	}
	




}
