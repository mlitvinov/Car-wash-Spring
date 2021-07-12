package com.aisa.repository;

import com.aisa.model.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrderRepository extends CrudRepository<Order, Integer>{
}
