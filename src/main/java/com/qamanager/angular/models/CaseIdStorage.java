package com.qamanager.angular.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "case_id_storage")
public class CaseIdStorage {
	
    String id;

    public CaseIdStorage() {
    	
    }
    
    public CaseIdStorage(String id) {
    	this.id = id;
    	
    }
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
}
