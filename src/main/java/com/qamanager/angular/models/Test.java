package com.qamanager.angular.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.core.mapping.Document;

@EntityScan
@Document(collection = "test")
public class Test {

	String id;

	String summary;
	
	String precondition;
	
	String status;
	
	String suiteId;
	
	String estimate;

	
	public Test() {
	}

	public Test(String summary, String precondition, String status, String suiteId, String estimate) {
		this.summary = summary;
		this.precondition = precondition;
		this.status = status;
		this.suiteId = suiteId;
		this.estimate = estimate;
	}

	public String getId() {
			return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPrecondition() {
		return precondition;
	}

	public void setPrecondition(String precondition) {
		this.precondition = precondition;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSuiteId() {

		return suiteId;
	}

	public void setSuiteId(String suiteId) {
		this.suiteId = suiteId;
	}

	public String getEstimate() {
		return estimate;
	}

	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}

}
