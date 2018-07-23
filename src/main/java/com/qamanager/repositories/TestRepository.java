package com.qamanager.repositories;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.qamanager.models.Test;

public interface TestRepository extends CrudRepository<Test, String> {

	@Override
	Test findOne(String id);

	@Override
	void delete(Test deleted);

	@Query("{suiteId : ?0}")
    Iterable<Test> findBySuiteIdQuery(String suiteId);

	
	
	@Query("{projectId : ?0, id : ?0}")
    Iterable<Test> findByProjectIdAndSuiteIdQuery(String projectId, String suiteId);
	
}
