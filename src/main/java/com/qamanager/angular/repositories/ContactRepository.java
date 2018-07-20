package com.qamanager.angular.repositories;

import org.springframework.data.repository.CrudRepository;

import com.qamanager.angular.models.Contact;

public interface ContactRepository extends CrudRepository<Contact, String> {
    @Override
    Contact findOne(String id);

    @Override
    void delete(Contact deleted);
}
