package cl.kvz.provida.oauth2.core.util;

import java.io.Serializable;

import org.apache.log4j.Logger;

import cl.kvz.provida.oauth2.core.exception.OauthException;
import cl.kvz.provida.oauth2.core.response.ResponseBuilder;

public class Reflection implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8377864621643596475L;
	private static final transient Logger LOGGER = Logger.getLogger( Reflection.class );
    private static final String FORMAT_CLASS_PREFIX = "cl.kvz.provida.oauth2.core.response.ResponseBuilder";

    private Reflection() {
    }

    @SuppressWarnings({ "unchecked", "static-access" })
    public static ResponseBuilder getResponseOauthObject(String type) throws OauthException {
        try {
            Class<ResponseBuilder> clazz = (Class<ResponseBuilder>) Reflection.class
                    .forName(FORMAT_CLASS_PREFIX + Utils.capitalize(type));
            LOGGER.debug(FORMAT_CLASS_PREFIX + Utils.capitalize(type));
            return clazz.newInstance();
        } catch (ClassNotFoundException e) {
            throw new OauthException(
                    "Format class not found for type, " + type, e);
        } catch (InstantiationException e) {
            throw new OauthException("Error to instantation object."
                    + type, e);
        } catch (IllegalAccessException e) {
            throw new OauthException(e);
        }
    }

}
