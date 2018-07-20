package com.qamanager.angular.controllers;


import com.qamanager.angular.models.Suite;
import com.qamanager.angular.repositories.ProjectRepository;
import com.qamanager.angular.repositories.SuiteRepository;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuiteController {

    @Autowired
    SuiteRepository suiteRepository;
    
    @Autowired
    ProjectRepository projectRepository;

    @SuppressWarnings("unchecked")
	@RequestMapping(method=RequestMethod.GET, value="/projects/{projectId}/suites")
    public ResponseEntity suite(@PathVariable String projectId) {
    	if(projectRepository.findOne(projectId)==null) {
    		return errorProjectNotFound(projectId);
    	}
    	
    	Iterable<Suite> suites = suiteRepository.findByProjectIdQuery(projectId);
        return new ResponseEntity(suites, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST, value="/projects/{projectId}/suites")
    public ResponseEntity save(@RequestBody Suite suite, @PathVariable String projectId) {
        
    	if(projectRepository.findOne(projectId)==null) {
    		return errorProjectNotFound(projectId);
    	}
    	
    	suite.setProjectId(projectId);
    	suiteRepository.save(suite);
    	return new ResponseEntity(suite, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.GET, value="/projects/{projectId}/suites/{id}")
    public ResponseEntity show(@PathVariable String projectId, @PathVariable String id) {
    	if(projectRepository.findOne(projectId)==null) {
    		return errorProjectNotFound(projectId);
    	}
    	return new ResponseEntity(suiteRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/projects/{projectId}/suites/{id}")
    public ResponseEntity update(@PathVariable String projectId, @PathVariable String id, @RequestBody Suite suite) {
    	if(projectRepository.findOne(projectId)==null) {
    		return errorProjectNotFound(projectId);
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

    @RequestMapping(method=RequestMethod.DELETE, value="/projects/{projectId}/suites/{id}")
    public ResponseEntity delete(@PathVariable String projectId, @PathVariable String id) {
    	if(projectRepository.findOne(projectId)==null) {
    		return errorProjectNotFound(projectId);
    	}
    	
        Suite suite = suiteRepository.findOne(id);
        suiteRepository.delete(suite);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    private ResponseEntity errorProjectNotFound(String projectId) {
    		Map<String, Object> map = new LinkedHashMap<>();
    		map.put("errorCode", HttpStatus.NOT_FOUND.toString());
    		map.put("errorMessage", "No project found with id "+projectId);
    		return new ResponseEntity(map, HttpStatus.NOT_FOUND);
    }
}
