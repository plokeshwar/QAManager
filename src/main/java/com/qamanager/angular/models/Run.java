package com.qamanager.angular.models;

import java.util.ArrayList;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.core.mapping.Document;

@EntityScan
@Document(collection = "run")
public class Run {
	

	String id;
	String suiteId;
	String name;
	String description;
	Boolean includeAll;
	ArrayList<String> caseArrays;
			
	public Run() {
	}

	public Run(String suiteId, String name, String description, Boolean includeAll, ArrayList<String> caseArrays) {
		this.suiteId = suiteId;
		this.name = name;
		this.description = description;
		this.includeAll = includeAll;
		this.caseArrays = caseArrays;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(String suiteId) {
		this.suiteId = suiteId;
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

	public Boolean getIncludeAll() {
		return includeAll;
	}

	public void setIncludeAll(Boolean includeAll) {
		this.includeAll = includeAll;
	}

	public ArrayList<String> getCaseArrays() {
		return caseArrays;
	}

	public void setCaseArrays(ArrayList<String> caseArrays) {
		this.caseArrays = caseArrays;
	}
	

}
