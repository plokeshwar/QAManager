package com.qamanager.angular.repositories;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.qamanager.angular.models.Step;

public interface StepRepository extends CrudRepository<Step, String> {

	@Override
	Step findOne(String id);

	@Override
	void delete(Step deleted);

	@Query("{test_id : ?0}")
    Iterable<Step> findByTestIdQuery(String test_id);

	
	@Query("{test_id : ?0, position : ?0}")
    Iterable<Step> findByProjectIdAndSuiteIdQuery(String test_id, int position);
    
	
}
