package de.hska.iwi.vslab.compositeserviceproductcategory;

import org.json.JSONObject;

public class Product {

	private int id;
	private String name;
	private double price;
	private String details;
	private int categoryID;

	
	public Product() {
	}


	public Product(int id, String name, double price, String details,  int categoryId) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.categoryID = categoryId;
		this.details = details;
	}
	
	public Product(String name, double price, String details,  int categoryId) {
		this.name = name;
		this.price = price;
		this.categoryID = categoryId;
		this.details = details;
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

	public int getCategoryId() {
		return this.categoryID;
	}

	public void setCategory(int category) {
		this.categoryID = category;
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
		prod.put("price", this.price);
		prod.put("categoryID", this.categoryID);
		prod.put("details", this.details);
		return prod;
	} 


}
