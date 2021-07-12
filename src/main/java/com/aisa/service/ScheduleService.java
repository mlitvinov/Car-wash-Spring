package com.aisa.service;


import com.aisa.model.entity.Schedule;

public interface ScheduleService {
    Iterable<Schedule> listAll();

    Schedule getById(Integer id);

    Schedule save(Schedule schedule);

    void delete(Integer id);

}
