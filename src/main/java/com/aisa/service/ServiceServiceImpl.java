package com.aisa.service;

import com.aisa.model.entity.Service;
import com.aisa.repository.ServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ServiceRepository serviceRepository;

    @Autowired
    public void setRepository(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Iterable<Service> listAll() {
        logger.debug("listAllServiceLists called");
        return serviceRepository.findAll();
    }

    @Override
    public Service getById(Integer id) {
        logger.debug("getServiceListById called");
        return serviceRepository.findById(id).orElse(null);
    }

    @Override
    public Service save(Service service) {
        logger.debug("saveServiceList called");
        return serviceRepository.save(service);
    }

    @Override
    public void delete(Integer id) {
        logger.debug("deleteServiceList called");
        serviceRepository.deleteById(id);
    }
}
