package de.hska.iwi.vslab.compositeserviceproductcategory;

import org.json.JSONObject;

public class Category{

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
