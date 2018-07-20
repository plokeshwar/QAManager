package com.qamanager.angular.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "project")
public class Project {
	
	@Id
    String id;
	String name;
    String description;

    public Project() {
    	
    }
    
    public Project(String name, String description) {
    	this.name = name;
    	this.description = name;
    	
    }
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
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


    
    
    
}
