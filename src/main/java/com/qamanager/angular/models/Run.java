package com.qamanager.angular.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.core.mapping.Document;

@EntityScan
@Document(collection = "run")
public class Run {
	
	final String appender = "R";

	String id;
	String suiteId;
	String name;
	String description;
	Boolean includeAll;
	String [] caseArrays;
			
	public Run() {
	}

	public Run(String suiteId, String name, String description, Boolean includeAll, String [] caseArrays) {
		this.suiteId = suiteId;
		this.name = name;
		this.description = description;
		this.includeAll = includeAll;
		this.caseArrays = caseArrays;
	}

	public String getId() {
		return appender+id;
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

	public String[] getCaseArrays() {
		return caseArrays;
	}

	public void setCaseArrays(String[] caseArrays) {
		this.caseArrays = caseArrays;
	}
	

}
