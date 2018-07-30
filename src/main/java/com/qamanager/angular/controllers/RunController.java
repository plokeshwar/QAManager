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
import com.qamanager.angular.models.Run;
import com.qamanager.angular.models.RunIdStorage;
import com.qamanager.angular.models.Test;
import com.qamanager.angular.repositories.CaseIdStorageRepository;
import com.qamanager.angular.repositories.ProjectRepository;
import com.qamanager.angular.repositories.RunIdStorageRepository;
import com.qamanager.angular.repositories.RunRepository;
import com.qamanager.angular.repositories.SuiteRepository;
import com.qamanager.angular.repositories.TestRepository;
import com.qamanager.angular.utilities.PropertiesLoader;

@RestController
public class RunController {


	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	RunIdStorageRepository runIdStorageRepository;
	
	@Autowired
	RunRepository runRepository;
	
	@Autowired
	SuiteRepository suiteRepository;


	@RequestMapping(method = RequestMethod.GET, value = "/projects/{projectId}/run")
	public ResponseEntity addRun(@PathVariable String projectId) {

		if (projectRepository.findOne(projectId) == null) {
			return errorNotFound("Project not found with id : " + projectId);
		}

		Iterable<Run> runs = runRepository.findAll();
		return new ResponseEntity(runs, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/projects/{projectId}/run")
	public ResponseEntity saveRun(@Valid @RequestBody Run run, @PathVariable String projectId) throws FileNotFoundException, IOException {
		

		if (projectRepository.findOne(projectId) == null) {
			return errorNotFound("Project not found with id : " + projectId);
		}

		run.setId("R"+getNewRunId());
		runRepository.save(run);
		return new ResponseEntity(run, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{projectId}/run/{runId}")
	public ResponseEntity updateRun(@Valid @RequestBody Run run, @PathVariable String projectId, @PathVariable String runId) throws FileNotFoundException, IOException {
		

		if (projectRepository.findOne(projectId) == null)
			return errorNotFound("Project not found with id : " + projectId);
		if(run.getSuiteId()!= null && suiteRepository.findOne(run.getSuiteId())==null)
			return errorNotFound("Suite not found with id : " + run.getSuiteId());
		
		Run r = runRepository.findOne(runId);
		if(run.getIncludeAll()!=null)
			r.setIncludeAll(run.getIncludeAll());
		if(run.getCaseArrays()!=null)
			r.setCaseArrays(run.getCaseArrays());
		if(run.getDescription()!=null)
			r.setDescription(run.getDescription());
		if(run.getName()!=null)
			r.setName(run.getName());
		if(run.getSuiteId()!=null)
			r.setSuiteId(run.getSuiteId());
		
		
		runRepository.save(r);
		return new ResponseEntity(r, HttpStatus.OK);
	}

	/*
	@RequestMapping(method = RequestMethod.PUT, value = "/suites/{suiteId}/tests/{testId}")
	public ResponseEntity update(@Valid @RequestBody Test test, @PathVariable String suiteId, @PathVariable String testId) throws FileNotFoundException, IOException {
	
		if (suiteRepository.findOne(suiteId) == null) {
			return errorNotFound("Suite not found with id : " + suiteId);
		}

		Test p = projectRepository.findOne(testId);
        if(test.getSummary()!= null)
            p.setSummary(test.getSummary());
        if(test.getPrecondition()!= null)
            p.setPrecondition(test.getPrecondition());
        if(test.getStatus()!=null)
        	p.setStatus(test.getStatus());
        if(test.getEstimate()!=null)
        	p.setEstimate(test.getEstimate());
        
        projectRepository.save(p);
    	return new ResponseEntity(p, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/suites/{suiteId}/tests/{testId}")
	public ResponseEntity delete(@PathVariable String suiteId, @PathVariable String testId) {
		if (suiteRepository.findOne(suiteId) == null) {
			return errorNotFound("Suite not found with id : " + suiteId);
		}

		Test test = projectRepository.findOne(testId);
		projectRepository.delete(test);

		return new ResponseEntity(HttpStatus.ACCEPTED);
	}

	*/

	private ResponseEntity errorNotFound(String message) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("errorCode", HttpStatus.NOT_FOUND.toString());
		map.put("errorMessage", message);
		return new ResponseEntity(map, HttpStatus.NOT_FOUND);
	}

	public String getNewRunId() {
		String id = "";
		Iterable<RunIdStorage> runList = runIdStorageRepository.findAll();
		RunIdStorage runIdStorage = new RunIdStorage();
		long counter = 0;
		if (runList.spliterator().getExactSizeIfKnown() > 0) {
			counter = runList.spliterator().getExactSizeIfKnown();
			counter++;
			id = String.valueOf(counter);

		} else {
			id = "1";
		}
		
		runIdStorage.setId(id);
		runIdStorageRepository.save(runIdStorage);
		return id;

	}
}
