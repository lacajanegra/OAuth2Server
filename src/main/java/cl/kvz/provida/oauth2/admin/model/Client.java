package cl.kvz.provida.oauth2.admin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 * 
 * @author MCastillo
 *
 */
@Entity
@Table(name = "dbo.oauth_client_app")
public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5991730665895067910L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "generate_id")
	private String generateID;
	
	@Column(name = "client_secret")
	private String clientSecret;

	@Column(name = "description")
	private String description;

	@Column(name = "home_uri")
	private String homeURI;

	@Column(name = "callback_uri")
	private String callbackURI;

	@Column(name = "time_expiration")
	private int timeExpiration;

	@Column(name = "state")
	private String state;

	@Column(name = "date_creation")
	private Date dateCreation;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_creation")
	private User userCreation;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = { CascadeType.MERGE })
	@JoinTable(name = "dbo.oauth_client_grant", joinColumns = { @JoinColumn(name = "client_id") }, inverseJoinColumns = { @JoinColumn(name = "grant_id") })
	private List<Grant> grants = new ArrayList<Grant>();


	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER )
	@JoinColumn(name="client_id", nullable=false)
	private List<Uri> uris = new ArrayList<Uri>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenerateID() {
		return generateID;
	}

	public void setGenerateID(String generateID) {
		this.generateID = generateID;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHomeURI() {
		return homeURI;
	}

	public void setHomeURI(String homeURI) {
		this.homeURI = homeURI;
	}

	public String getCallbackURI() {
		return callbackURI;
	}

	public void setCallbackURI(String callbackURI) {
		this.callbackURI = callbackURI;
	}

	public int getTimeExpiration() {
		return timeExpiration;
	}

	public void setTimeExpiration(int timeExpiration) {
		this.timeExpiration = timeExpiration;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public User getUserCreation() {
		return userCreation;
	}

	public void setUserCreation(User userCreation) {
		this.userCreation = userCreation;
	}

	public List<Grant> getGrants() {
		return grants;
	}

	public void setGrants(List<Grant> grants) {
		this.grants = grants;
	}

	public List<Uri> getUris() {
		return uris;
	}

	public void setUris(List<Uri> uris) {
		this.uris = uris;
	}

}
