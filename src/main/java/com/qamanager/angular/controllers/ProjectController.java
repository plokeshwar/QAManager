package com.qamanager.angular.controllers;

import com.qamanager.angular.models.Project;
import com.qamanager.angular.repositories.ProjectRepository;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @RequestMapping(method=RequestMethod.GET, value="/projects")
    public Iterable<Project> project() {
        return projectRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/projects")
    public Project save(@Valid @RequestBody Project project) {
        projectRepository.save(project);

        return project;
    }

    @RequestMapping(method=RequestMethod.GET, value="/projects/{id}")
    public Project show(@PathVariable String id) {
        return projectRepository.findOne(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/projects/{id}")
    public Project update(@PathVariable String id, @Valid @RequestBody Project project) {
    	Project p = projectRepository.findOne(id);
        if(project.getName() != null)
            p.setName(project.getName());
        if(project.getDescription()!= null)
            p.setDescription(project.getDescription());
        projectRepository.save(p);
        return project;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/projects/{id}")
    public String delete(@PathVariable String id) {
        Project project = projectRepository.findOne(id);
        projectRepository.delete(project);

        return "";
    }
}
