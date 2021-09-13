package hska.iwi.eShopMaster.model.businessLogic.manager.REST;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.json.JSONObject;


@Entity // This tells Hibernate to make a table out of this class
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private int permission; // This replaces the role definition

	public Account() {
	}

	public Account(String username, String firstname, String lastname, String password, int permission) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.permission = permission;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPermission() {
		return this.permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	
	public JSONObject getJSONObject(){
		JSONObject cat = new JSONObject();
		cat.put("id", this.id);
		cat.put("username", this.username);
		cat.put("firstname", this.firstname);
		cat.put("lastname", this.lastname);
		cat.put("password", this.password);
		cat.put("permission", this.permission);
		return cat;
	} 

}
