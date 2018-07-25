package com.qamanager.angular.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.core.mapping.Document;

@EntityScan
@Document(collection = "steps")
public class Step {

	String id;
	String test_id;
	long position;
	String step;
	String expected_result;
	
	public Step() {

	}

	public Step(String test_id, long position, String step, String expected_result) {
		this.test_id=test_id;
		this.position = position;
		this.step = step;
		this.expected_result = expected_result;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTest_id() {
		return test_id;
	}

	public void setTest_id(String test_id) {
		this.test_id = test_id;
	}

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getExpected_result() {
		return expected_result;
	}

	public void setExpected_result(String expected_result) {
		this.expected_result = expected_result;
	}


}
