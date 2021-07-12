package com.aisa.repository;

import com.aisa.model.entity.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ServiceRepository extends CrudRepository<Service, Integer>{
}
