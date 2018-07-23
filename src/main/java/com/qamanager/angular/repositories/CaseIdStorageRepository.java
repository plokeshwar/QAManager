package com.qamanager.angular.repositories;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.qamanager.angular.models.CaseIdStorage;
import com.qamanager.angular.models.Test;

public interface CaseIdStorageRepository extends CrudRepository<CaseIdStorage, String> {

	@Override
	CaseIdStorage findOne(String id);

	
	
}
