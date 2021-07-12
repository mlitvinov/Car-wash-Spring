package com.aisa.service;

import com.aisa.model.entity.Order;
import com.aisa.model.entity.Schedule;
import com.aisa.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private OrderRepository orderRepository;
    private OrderService orderService;

    private ServiceService serviceService;
    private ScheduleService scheduleService;

    @Autowired
    public void setRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setOrderService(OrderService orderService, ServiceService serviceService, ScheduleService scheduleService) {
        this.orderService = orderService;
        this.serviceService = serviceService;
        this.scheduleService = scheduleService;
    }

    @Override
    public Iterable<Order> listAll() {
        logger.debug("listAllOrder called");
        return orderRepository.findAll();
    }

    @Override
    public Order getById(Integer id) {
        logger.debug("getOrderById called");
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order save(Order order) {
        logger.debug("saveOrder called");
        return orderRepository.save(order);
    }

    @Override
    public boolean isItServiceAvailable(Order Order) {
        for (com.aisa.model.entity.Service service : serviceService.listAll()) {
            if (Objects.equals(service.getService_name(), Order.getService())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isItTimeAvailable(Order Order) {
        for (Schedule schedule : scheduleService.listAll()) {
            if (Objects.equals(schedule.getTime(), Order.getTime())) {
                orderService.save(Order);
                scheduleService.delete(schedule.getId());
                return true;
            }
        }
        return false;
    }

    @Override
    public void delete(Integer id) {
        logger.debug("deleteOrder called");
        orderRepository.deleteById(id);
    }

    @Override
    public String calculate(String time) {
        Instant start = Instant.now().plusSeconds(10800);
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Instant s = Instant.parse(timeStamp + "T" + time + ":00.007Z");
        Duration duration = Duration.between(start, s);
        if (duration.toMillis() < 0) {
            duration = duration.plusSeconds(86400);
            System.out.println();
        }
        long hours = duration.toHours();
        long min = duration.toMinutes() - (60 * hours);
        return +hours + " Часов " + min + " минут ";
    }
}
