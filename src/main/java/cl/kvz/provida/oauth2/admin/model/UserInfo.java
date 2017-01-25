package cl.kvz.provida.oauth2.admin.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author MCastillo
 *
 */

public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String name;
	private String mail;
	private String infinity_id;
	

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getInfinityId() {
		return infinity_id;
	}

	public void setInfinityId(String infinity_id) {
		this.infinity_id = infinity_id;
	}
	



}
