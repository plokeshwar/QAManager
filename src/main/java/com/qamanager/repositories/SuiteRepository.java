package com.qamanager.repositories;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.qamanager.models.Suite;

public interface SuiteRepository extends CrudRepository<Suite, String> {

	@Override
	Suite findOne(String id);

	@Override
	void delete(Suite deleted);

	@Query("{projectId : ?0}")
    Iterable<Suite> findByProjectIdQuery(String projectId);

	@Query("{projectId : ?0, id : ?0}")
    Iterable<Suite> findByProjectIdAndSuiteIdQuery(String projectId, String suiteId);
	
}
