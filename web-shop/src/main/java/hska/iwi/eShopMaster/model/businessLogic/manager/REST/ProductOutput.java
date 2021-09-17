package hska.iwi.eShopMaster.model.businessLogic.manager.REST;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.json.JSONObject;

@Entity // This tells Hibernate to make a table out of this class
public class ProductOutput {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private double price;
	private String details;
	private String categoryName;
	private int categoryID;

	public ProductOutput() {
	}

	public ProductOutput(String name, double price, String details, String categoryName, int categoryID) {
		this.name = name;
		this.price = price;
		this.details = details;
		this.categoryName = categoryName;
		this.categoryID = categoryID;
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

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategory(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getCategoryID() {
		return this.categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public JSONObject getJSONObject() {
		JSONObject prod = new JSONObject();

		prod.put("id", this.id);
		prod.put("name", this.name);
		prod.put("price", this.price);
		prod.put("categoryName", this.categoryName);
		prod.put("details", this.details);
		return prod;
	}

	public String toString() {

		return "id:" + id + " name:" + name + " price:" + price + " categoryName:" + categoryName + " categoryID:"
				+ categoryID + " details:" + details;

	}
}
