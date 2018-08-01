package com.qamanager.angular.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.qamanager.angular.models.Project;
import com.qamanager.angular.models.Suite;

public interface ProjectRepository extends CrudRepository<Project, String> {

	@Override
	Project findOne(String id);

	@Override
	void delete(Project deleted);
	
	@Query("{name : ?0}")
    Project findByProjectByName(String projectName);

}
