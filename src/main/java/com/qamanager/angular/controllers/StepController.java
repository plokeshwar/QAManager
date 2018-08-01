package com.qamanager.angular.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qamanager.angular.models.Step;
import com.qamanager.angular.repositories.StepRepository;
import com.qamanager.angular.repositories.TestRepository;
import com.qamanager.angular.utilities.ApiError;

@RequestMapping("/api/v1")
@RestController
public class StepController {

	@Autowired
	StepRepository stepRepository;

	@Autowired
	TestRepository testRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/tests/{testId}/steps")
	public ResponseEntity getSteps(@PathVariable String testId) {

		if (testRepository.findOne(testId) == null) {
			return ApiError.errorNotFound("Test not found with id : " + testId);
		}

		Iterable<Step> steps = stepRepository.findByTestIdQuery(testId);
		return new ResponseEntity(steps, HttpStatus.OK);
	}

	
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{testId}/steps")
	public ResponseEntity createStep(@Valid @RequestBody Step step, @PathVariable String testId) {

		if (testRepository.findOne(testId) == null) {
			return ApiError.errorNotFound("Test not found with id : " + testId);
		}
		Iterable<Step> steps = stepRepository.findByTestIdQuery(testId);
		long counter = steps.spliterator().getExactSizeIfKnown();
			counter++;
			step.setPosition(counter);
		
		step.setTest_id(testId);
		stepRepository.save(step);
		return new ResponseEntity(step, HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.PUT, value = "/tests/{testId}/steps/{stepId}")
	public ResponseEntity update(@Valid @RequestBody Step step, @PathVariable String testId, @PathVariable String stepId) {
	
		if (testRepository.findOne(testId) == null) {
			return ApiError.errorNotFound("Test not found with id : " + testId);
		}

		Step p = stepRepository.findOne(stepId);
        if(step.getTest_id()!= null)
            p.setTest_id(step.getTest_id());
        if(step.getStep()!=null)
        	p.setStep(step.getStep());
        if(step.getExpected_result()!= null)
        	p.setExpected_result(step.getExpected_result());
        if(step.getPosition() != 0)
        	p.setPosition(step.getPosition());
        
        stepRepository.save(p);
    	return new ResponseEntity(p, HttpStatus.OK);
	}

	/*
	@RequestMapping(method = RequestMethod.DELETE, value = "/suites/{suiteId}/tests/{testId}")
	public ResponseEntity delete(@PathVariable String suiteId, @PathVariable String testId) {
		if (suiteRepository.findOne(suiteId) == null) {
			return ApiError.errorNotFound("Suite not found with id : " + suiteId);
		}

		Test test = testRepository.findOne(testId);
		testRepository.delete(test);

		return new ResponseEntity(HttpStatus.ACCEPTED);
	}


	

*/
	
	
}
