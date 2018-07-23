package com.qamanager.angular.repositories;


import org.springframework.data.repository.CrudRepository;

import com.qamanager.angular.models.CaseIdStorage;

public interface CaseIdStorageRepository extends CrudRepository<CaseIdStorage, String> {

	@Override
	CaseIdStorage findOne(String id);

	
	
}
