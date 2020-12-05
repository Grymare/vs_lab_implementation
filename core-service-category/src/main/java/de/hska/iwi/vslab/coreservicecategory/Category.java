package de.hska.iwi.vslab.coreservicecategory;


    
    
public class Category implements java.io.Serializable {

	private int id;
	private String name;

	public Category() {
	}

	public Category(String name) {
        //TODO: how do we get a random UNUSED new ID?
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

}
