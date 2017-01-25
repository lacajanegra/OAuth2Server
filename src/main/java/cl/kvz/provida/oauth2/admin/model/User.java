package cl.kvz.provida.oauth2.admin.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 * 
 * @author MCastillo
 *
 */
@Entity
@Table(name = "dbo.oauth_user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3923068766105726944L;
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "user_creation")
	private String userCreation;
	
	@Column(name = "date_creation")
	private Date dateCreation;
	
	@Column(name = "perfil")
	private String perfil;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserCreation() {
		return userCreation;
	}

	public void setUserCreation(String userCreation) {
		this.userCreation = userCreation;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

}
