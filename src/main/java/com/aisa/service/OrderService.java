package com.aisa.service;


import com.aisa.model.entity.Order;

public interface OrderService {
    Iterable<Order> listAll();

    Order getById(Integer id);

    Order save(Order order);



    boolean isItServiceAvailable(Order Order);

    boolean isItTimeAvailable(Order Order);

    void delete(Integer id);

    String calculate(String time);
}
