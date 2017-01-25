package cl.kvz.provida.oauth2.admin.model.code;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import cl.kvz.provida.oauth2.admin.model.Client;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 * 
 * @author MCastillo
 *
 */
@Entity
@Table(name = "dbo.oauth_client_token")
public class Token implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6262059597189061221L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "token")
	private String token;
	
	@Column(name = "date_creation")
	private Date dateCreation;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "client_id")
	private Client client;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name="token_id", nullable=false)
	private List<RefreshToken> refreshtokens = new ArrayList<RefreshToken>();


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<RefreshToken> getRefreshtokens() {
		return refreshtokens;
	}

	public void setRefreshtokens(List<RefreshToken> refreshtokens) {
		this.refreshtokens = refreshtokens;
	}

	
}
