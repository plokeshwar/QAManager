package com.qamanager.angular.repositories;

import org.springframework.data.repository.CrudRepository;

import com.qamanager.angular.models.Project;

public interface ProjectRepository extends CrudRepository<Project, String> {

	@Override
	Project findOne(String id);

	@Override
	void delete(Project deleted);

}
