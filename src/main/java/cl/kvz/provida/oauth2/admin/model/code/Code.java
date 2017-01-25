package cl.kvz.provida.oauth2.admin.model.code;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cl.kvz.provida.oauth2.admin.model.Client;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 * 
 * @author MCastillo
 *
 */
@Entity
@Table(name = "dbo.oauth_client_code")
public class Code implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1911790669034308818L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "code")
	private String code;
	
	@Column(name = "date_creation")
	private Date dateCreation;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "client_id")
	private Client client;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}
