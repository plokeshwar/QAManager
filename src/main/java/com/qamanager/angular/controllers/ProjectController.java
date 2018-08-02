package com.qamanager.angular.controllers;

import com.qamanager.angular.models.Project;
import com.qamanager.angular.repositories.ProjectRepository;
import com.qamanager.angular.utilities.ApiError;

import java.util.LinkedHashMap;
import java.util.Map;

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

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class ProjectController {

	@Autowired
	ProjectRepository projectRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/projects")
	public Iterable<Project> getProjects() {
		return projectRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/projects")
	public ResponseEntity saveProject(@Valid @RequestBody Project project) {

		Project p = projectRepository.findByProjectByName(project.getName());
		
		if (p != null)
			return ApiError.errorDuplicate(project.getName() + " project already exists.");

		projectRepository.save(project);
		return new ResponseEntity(project, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/projects/{id}")
	public Project getProject(@PathVariable String id) {
		return projectRepository.findOne(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{id}")
	public ResponseEntity updateProject(@PathVariable String id, @Valid @RequestBody Project project) {

		if (projectRepository.findOne(id) == null)
			return ApiError.errorNotFound("Project not found with id " + id);

		Project p = projectRepository.findOne(id);
		if (project.getName() != null)
			p.setName(project.getName());
		if (project.getDescription() != null)
			p.setDescription(project.getDescription());
		projectRepository.save(p);
		return new ResponseEntity(p, HttpStatus.OK);
	}
	
	

	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{id}")
	public String deleteProject(@PathVariable String id) {
		if (projectRepository.findOne(id) == null)
			ApiError.errorNotFound("Project not found with id " + id);
		Project project = projectRepository.findOne(id);
		projectRepository.delete(project);

		return "";
	}

	
}
