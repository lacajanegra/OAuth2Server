package cl.kvz.provida.oauth2.admin.model.code;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 * 
 * @author MCastillo
 *
 */
@Entity
@Table(name = "dbo.oauth_refresh_token")
public class RefreshToken implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -133585951649579016L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "refresh_token")
	private String refreshToken;
	
	@Column(name = "date_creation")
	private Date dateCreation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	

	
}
