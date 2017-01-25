package cl.kvz.provida.oauth2.core.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Constants {

	
	
	
	public static final String SPRING_CONFIG				= Configurations.getInstance().getProperty("SPRING_CONFIG");
	
	public static final String ACTIVE 						= Configurations.getInstance().getProperty("ACTIVE");
	public static final String DELETED 						= Configurations.getInstance().getProperty("DELETED");
	
	public static final String TOKEN 						= Configurations.getInstance().getProperty("TOKEN");
	public static final String AUTH_CODE 					= Configurations.getInstance().getProperty("AUTH_CODE");
	public static final String REFRESH 						= Configurations.getInstance().getProperty("REFRESH");
	public static final String INVALID_CLIENT 				= Configurations.getInstance().getProperty("INVALID_CLIENT");
	public static final String INVALID_GRANT 				= Configurations.getInstance().getProperty("INVALID_GRANT");
	public static final String INVALID_CODE					= Configurations.getInstance().getProperty("INVALID_CODE");
	
	public static final String INVALID_CLIENT_DESCRIPTION 	= Configurations.getInstance().getProperty("INVALID_CLIENT_DESCRIPTION");
	public static final String INVALID_GRANT_DESCRIPTION 	= Configurations.getInstance().getProperty("INVALID_GRANT_DESCRIPTION");

	
	

	public static final String AUTH_CODE_URI				= Configurations.getInstance().getProperty("AUTH_CODE_URI");
	public static final String USER 						= Configurations.getInstance().getProperty("USER");
	public static final String URICOOKIE					= Configurations.getInstance().getProperty("URICOOKIE");
	public static final String LOGIN_CLIENT 				= Configurations.getInstance().getProperty("LOGIN_CLIENT");

	public static final Object LDAP_S 						= Configurations.getInstance().getProperty("LDAP_S");
	public static final String LDAP_URL 					= Configurations.getInstance().getProperty("LDAP_URL");
	//public static final String LDAP_URL 					= "http://10.1.249.191:12000/ws/CL_1008_WSNACAR.pub.ws.claveAcceso:wsValida?WSDL"; //URL BACKUP (solo test, no oficial)	
	
	public static final String TOKEN_INACTIVE				= Configurations.getInstance().getProperty("TOKEN_INACTIVE");


	
	public static final String ERROR_LDAP					= Configurations.getInstance().getProperty("ERROR_LDAP");
	public static final String ERROR_KN						= Configurations.getInstance().getProperty("ERROR_KN");
	
	public static final String LLAMADA_MQ					= Configurations.getInstance().getProperty("LLAMADA_MQ");
	
	//set cookies
	//public static final String DEVDOMAIN					= "localhost";
	public static final String DEVDOMAIN					= Configurations.getInstance().getProperty("DEVDOMAIN");
	//public static final String DEVDOMAIN					= ".prov-inf-qa.provida.cl";
	
	public static final String PATH_CONFFILE = "properties/config.properties";
	public static final int MYCONSTANT_ONE = 1;
	
}
