/**
 * 
 */
package cl.kvz.provida.oauth2.core.pojo;

import java.io.Serializable;

public class FormUser implements Serializable {
	private static final long serialVersionUID = 2474571247263287888L;

	private String user;
	private String pass;

	public FormUser() {

	}


	public FormUser(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
