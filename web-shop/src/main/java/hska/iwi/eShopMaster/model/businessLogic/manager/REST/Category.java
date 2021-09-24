package hska.iwi.eShopMaster.model.businessLogic.manager.REST;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.json.JSONObject;

@Entity // This tells Hibernate to make a table out of this class
public class Category{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;

	
	public Category() {
	}

	public Category(String name) {
		this.name = name;
	}

	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Category other) {
		if (this.id == other.id && this.name.equals(other.name)){
			return true;
		}else{
			return false;
		}
	}

	public JSONObject getJSONObject(){
		JSONObject cat = new JSONObject();
		cat.put("id", this.id);
		cat.put("name", this.name);
		return cat;
	} 
}
