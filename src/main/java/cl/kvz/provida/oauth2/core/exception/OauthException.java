package cl.kvz.provida.oauth2.core.exception;

public class OauthException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7315305469090091453L;

	public OauthException(String message) {
        super(message);
    }

    public OauthException(String message, Throwable cause) {
        super(message, cause);
    }

    public OauthException(Throwable cause) {
        super(cause);
    }
}
