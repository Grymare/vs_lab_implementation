package hska.iwi.eShopMaster.model.businessLogic.manager.REST;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.json.JSONObject;

@Entity // This tells Hibernate to make a table out of this class
public class ProductOutput {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private double price;
	private String details;
	private String categoryString;

	
	public ProductOutput() {
	}

	public ProductOutput(String name, double price, String details,  String categoryString) {
		this.name = name;
		this.price = price;
		this.details = details;
		this.categoryString = categoryString;
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

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategoryString() {
		return this.categoryString;
	}

	public void setCategory(String categoryString) {
		this.categoryString = categoryString;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public JSONObject getJSONObject(){
		JSONObject prod = new JSONObject();

		prod.put("id", this.id);
		prod.put("name", this.name);
		prod.put("price", this.price);
		prod.put("categoryString", this.categoryString);
		prod.put("details", this.details);
		return prod;
	} 


}
