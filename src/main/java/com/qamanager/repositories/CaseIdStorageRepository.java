package com.qamanager.repositories;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.qamanager.models.CaseIdStorage;
import com.qamanager.models.Test;

public interface CaseIdStorageRepository extends CrudRepository<CaseIdStorage, String> {

	@Override
	CaseIdStorage findOne(String id);

	
	
}
