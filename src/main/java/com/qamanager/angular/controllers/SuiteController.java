package com.qamanager.angular.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qamanager.angular.models.Suite;
import com.qamanager.angular.repositories.ProjectRepository;
import com.qamanager.angular.repositories.SuiteRepository;
import com.qamanager.angular.utilities.ApiError;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api/v1")
@RestController
public class SuiteController {

    @Autowired
    SuiteRepository suiteRepository;
    
    @Autowired
    ProjectRepository projectRepository;

    
	@RequestMapping(method=RequestMethod.GET, value="/projects/{projectId}/suites")
    public ResponseEntity suite(@PathVariable String projectId) {
    	if(projectRepository.findOne(projectId)==null) {
    		return ApiError.errorNotFound(projectId);
    	}
    	
    	Iterable<Suite> suites = suiteRepository.findByProjectIdQuery(projectId);
        return new ResponseEntity(suites, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST, value="/projects/{projectId}/suites")
    public ResponseEntity save(@Valid @RequestBody Suite suite, @PathVariable String projectId) {
        
    	if(projectRepository.findOne(projectId)==null)
    		return ApiError.errorNotFound(projectId);
    	
    	Iterable<Suite> suites = suiteRepository.findByProjectIdQuery(projectId);
    	
    		for(Suite s : suites) {
    			if(s.getName().equals(suite.getName())) {
    				return ApiError.errorDuplicate(suite.getName()+" suite already exists.");
    			}
    		}
    	
    	
    	
    	suite.setProjectId(projectId);
    	suiteRepository.save(suite);
    	return new ResponseEntity(suite, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.GET, value="/projects/{projectId}/suites/{id}")
    public ResponseEntity show(@PathVariable String projectId, @PathVariable String id) {
    	if(projectRepository.findOne(projectId)==null) 
    		return ApiError.errorNotFound(projectId);
    	
    	return new ResponseEntity(suiteRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/projects/{projectId}/suites/{id}")
    public ResponseEntity update(@PathVariable String projectId, @PathVariable String id, @Valid @RequestBody Suite suite) {
    	if(projectRepository.findOne(projectId)==null) 
    		return ApiError.errorNotFound(projectId);
    	
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

    @RequestMapping(method=RequestMethod.DELETE, value="/projects/{projectId}/suites/{id}")
    public ResponseEntity delete(@PathVariable String projectId, @PathVariable String id) {
    	if(projectRepository.findOne(projectId)==null) {
    		return ApiError.errorNotFound(projectId);
    	}
    	
        Suite suite = suiteRepository.findOne(id);
        suiteRepository.delete(suite);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
}
