package com.aisa.repository;

import com.aisa.model.entity.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ScheduleRepository extends CrudRepository<Schedule, Integer>{
}
