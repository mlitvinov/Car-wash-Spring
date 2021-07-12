package com.aisa.service;

import com.aisa.model.entity.Schedule;
import com.aisa.repository.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleImpl implements ScheduleService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ScheduleRepository scheduleRepository;

    @Autowired
    public void setRepository(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Iterable<Schedule> listAll() {
        logger.debug("listAllSchedule called");
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getById(Integer id) {
        logger.debug("getScheduleById called");
        return scheduleRepository.findById(id).orElse(null);
    }

    @Override
    public Schedule save(Schedule schedule) {
        logger.debug("saveSchedule called");
        return scheduleRepository.save(schedule);
    }

    @Override
    public void delete(Integer id) {
        logger.debug("deleteSchedule called");
        scheduleRepository.deleteById(id);
    }
}
