package com.qamanager.angular.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "suite")
public class Suite {

	@Id
	String id;
	
	@NotNull
	@Size(min=5,max=30, message="Suite name should be minimum 5 and max 30 characters long.")
	String name;
	
	@Size(max=30, message="Suite description should be max 1000 characters long.")
	String description;
	
	String projectId;

	public Suite() {

	}

	public Suite(String name, String description, String projectId) {
		this.name = name;
		this.description = name;
		this.projectId = projectId;

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

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

}
