package com.qamanager.angular.repositories;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.qamanager.angular.models.Run;
import com.qamanager.angular.models.Test;

public interface RunRepository extends CrudRepository<Run, String> {

	@Override
	Run findOne(String id);

	@Override
	void delete(Run deleted);

	@Query("{id : ?0}")
    Iterable<Run> findByRunIdQuery(String runId);

		
}
