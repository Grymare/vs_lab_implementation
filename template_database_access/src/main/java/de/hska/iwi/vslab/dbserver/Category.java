package de.hska.iwi.vslab.dbserver;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Category {

    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;
	/*
	public Category(int id, String name) {
        this.id = id;
		this.name = name;
    }*/    

	public int getId() {
		return this.id;
    }
    public void setId(int id ){
        this.id = id;
    }

	public String getName() {
		return this.name;
	}

    public void setName(String name ){
        this.name = name;
	}
	
	//TODO: equals 

}