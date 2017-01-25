package cl.kvz.provida.oauth2.admin.model;

import java.io.Serializable;

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
@Table(name = "dbo.oauth_grant")
public class Grant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6553165108028150873L;

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
	
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "grants", targetEntity = Client.class)
//	private List<Client> clients = new ArrayList<Client>();

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public List<Client> getClients() {
//		return clients;
//	}
//
//	public void setClients(List<Client> clients) {
//		this.clients = clients;
//	}

}
