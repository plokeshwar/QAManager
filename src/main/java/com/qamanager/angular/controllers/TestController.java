package com.qamanager.angular.controllers;


import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qamanager.angular.models.Test;
import com.qamanager.angular.repositories.SuiteRepository;
import com.qamanager.angular.repositories.TestRepository;

@RestController
public class TestController {

    @Autowired
    SuiteRepository suiteRepository;
    
    @Autowired
    TestRepository testRepository;

    @SuppressWarnings("unchecked")
	@RequestMapping(method=RequestMethod.GET, value="/suites/{suiteId}/tests")
    public ResponseEntity test(@PathVariable String suiteId) {
    	
    	if(suiteRepository.findOne(suiteId)==null) {
    		return errorNotFound("Suite not found with id : "+suiteId);
    	}
    	
    	Iterable<Test> tests = testRepository.findBySuiteIdQuery(suiteId);
        return new ResponseEntity(tests, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST, value="/suites/{suiteId}/tests")
    public ResponseEntity save(@RequestBody Test test, @PathVariable String suiteId) {
        
    	if(suiteRepository.findOne(suiteId)==null) {
    		return errorNotFound("Suite not found with id : "+suiteId);
    	}
    	
    	test.setSuiteId(suiteId);
    	testRepository.save(test);
    	return new ResponseEntity(test, HttpStatus.OK);
    }
    
    @RequestMapping(method=RequestMethod.DELETE, value="/suites/{suiteId}/tests/{testId}")
    public ResponseEntity delete(@PathVariable String suiteId, @PathVariable String testId) {
    	if(suiteRepository.findOne(suiteId)==null) {
    		return errorNotFound("Suite not found with id : "+suiteId);
    	}
    	
        Test test = testRepository.findOne(testId);
        testRepository.delete(test);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
/*
    @RequestMapping(method=RequestMethod.GET, value="/projects/{projectId}/suites/{id}")
    public ResponseEntity show(@PathVariable String projectId, @PathVariable String id) {
    	if(testRepository.findOne(projectId)==null) {
    		return errorNotFound(projectId);
    	}
    	return new ResponseEntity(suiteRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/projects/{projectId}/suites/{id}")
    public ResponseEntity update(@PathVariable String projectId, @PathVariable String id, @RequestBody Suite suite) {
    	if(testRepository.findOne(projectId)==null) {
    		return errorNotFound(projectId);
    	}
    	
    	Suite p = suiteRepository.findOne(id);
        if(suite.getName() != null)
            p.setName(suite.getName());
        if(suite.getDescription()!= null)
            p.setDescription(suite.getDescription());
        if(suite.getProjectId() != null)
        	p.setProjectId(suite.getProjectId());
        suiteRepository.save(p);
        return new ResponseEntity(p, HttpStatus.OK);
    }

   
    */
    
    
    
    
    private ResponseEntity errorNotFound(String message) {
    		Map<String, Object> map = new LinkedHashMap<>();
    		map.put("errorCode", HttpStatus.NOT_FOUND.toString());
    		map.put("errorMessage", message);
    		return new ResponseEntity(map, HttpStatus.NOT_FOUND);
    }
}
