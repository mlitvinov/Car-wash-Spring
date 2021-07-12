package com.aisa.service;


import com.aisa.model.entity.Service;

public interface ServiceService {
    Iterable<Service> listAll();

    Service getById(Integer id);

    Service save(Service service);

    void delete(Integer id);

}
