package com.qamanager.angular.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qamanager.angular.models.CaseIdStorage;
import com.qamanager.angular.models.Test;
import com.qamanager.angular.repositories.CaseIdStorageRepository;
import com.qamanager.angular.repositories.SuiteRepository;
import com.qamanager.angular.repositories.TestRepository;
import com.qamanager.angular.utilities.PropertiesLoader;

@RestController
public class TestController {

	@Autowired
	SuiteRepository suiteRepository;

	@Autowired
	TestRepository testRepository;

	@Autowired
	CaseIdStorageRepository caseIdStorageRepository;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/suites/{suiteId}/tests")
	public ResponseEntity test(@PathVariable String suiteId) {

		if (suiteRepository.findOne(suiteId) == null) {
			return errorNotFound("Suite not found with id : " + suiteId);
		}

		Iterable<Test> tests = testRepository.findBySuiteIdQuery(suiteId);
		return new ResponseEntity(tests, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/suites/{suiteId}/tests")
	public ResponseEntity save(@Valid @RequestBody Test test, @PathVariable String suiteId) throws FileNotFoundException, IOException {
		Properties prop = PropertiesLoader.getProperties();

		if (suiteRepository.findOne(suiteId) == null) {
			return errorNotFound("Suite not found with id : " + suiteId);
		}

		test.setId(prop.getProperty("testCaseStart")+"-"+getNewCaseId());
		test.setSuiteId(suiteId);
		testRepository.save(test);
		return new ResponseEntity(test, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/suites/{suiteId}/tests/{testId}")
	public ResponseEntity update(@Valid @RequestBody Test test, @PathVariable String suiteId, @PathVariable String testId) throws FileNotFoundException, IOException {
	
		if (suiteRepository.findOne(suiteId) == null) {
			return errorNotFound("Suite not found with id : " + suiteId);
		}

		Test p = testRepository.findOne(testId);
        if(test.getSummary()!= null)
            p.setSummary(test.getSummary());
        if(test.getPrecondition()!= null)
            p.setPrecondition(test.getPrecondition());
        if(test.getStatus()!=null)
        	p.setStatus(test.getStatus());
        if(test.getEstimate()!=null)
        	p.setEstimate(test.getEstimate());
        
        testRepository.save(p);
    	return new ResponseEntity(p, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/suites/{suiteId}/tests/{testId}")
	public ResponseEntity delete(@PathVariable String suiteId, @PathVariable String testId) {
		if (suiteRepository.findOne(suiteId) == null) {
			return errorNotFound("Suite not found with id : " + suiteId);
		}

		Test test = testRepository.findOne(testId);
		testRepository.delete(test);

		return new ResponseEntity(HttpStatus.ACCEPTED);
	}


	private ResponseEntity errorNotFound(String message) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("errorCode", HttpStatus.NOT_FOUND.toString());
		map.put("errorMessage", message);
		return new ResponseEntity(map, HttpStatus.NOT_FOUND);
	}

	public String getNewCaseId() {
		String id = "";
		CaseIdStorage caseId = new CaseIdStorage();
		Iterable<CaseIdStorage> caseList = caseIdStorageRepository.findAll();
		long counter = 0;
		if (caseList.spliterator().getExactSizeIfKnown() > 0) {
			counter = caseList.spliterator().getExactSizeIfKnown();
			counter++;
			id = String.valueOf(counter);

		} else {
			id = "1";
		}
		
		caseId.setId(id);
		caseIdStorageRepository.save(caseId);
		return id;

	}
}
