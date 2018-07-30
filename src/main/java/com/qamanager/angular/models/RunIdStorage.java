package com.qamanager.angular.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "run_id_storage")
public class RunIdStorage {
	
	
    String id;

    public RunIdStorage() {
    	
    }
    
    public RunIdStorage(String id) {
    	this.id = id;
    	
    }
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		
		this.id = id;
	}
    
}
