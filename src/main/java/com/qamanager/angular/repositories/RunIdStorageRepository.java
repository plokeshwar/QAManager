package com.qamanager.angular.repositories;


import org.springframework.data.repository.CrudRepository;

import com.qamanager.angular.models.RunIdStorage;

public interface RunIdStorageRepository extends CrudRepository<RunIdStorage, String> {

	@Override
	RunIdStorage findOne(String id);

	
	
}
