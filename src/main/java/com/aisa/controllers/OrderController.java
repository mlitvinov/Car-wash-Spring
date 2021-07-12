package com.aisa.controllers;

import com.aisa.model.entity.Order;

import com.aisa.service.OrderService;
import com.aisa.service.ScheduleService;
import com.aisa.service.ServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Api(value = "order", description = "operations with orders")
public class OrderController {

    private OrderService orderService;
    private ServiceService serviceService;
    private ScheduleService scheduleService;

    @Autowired
    public void setOrderService(OrderService orderService, ServiceService serviceService,ScheduleService scheduleService) {
        this.orderService = orderService;
        this.serviceService = serviceService;
        this.scheduleService = scheduleService;
    }

    @ApiOperation(value = "View a list of clients", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public Iterable<Order> list(Model model) {
        Iterable<Order> orders = orderService.listAll();
        return orders;
    }


    @ApiOperation(value = "Book a visit time")
    @RequestMapping(value = "/book", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveOrder(@RequestBody Order Order) {
        if (orderService.isItServiceAvailable(Order)) {
            if (orderService.isItTimeAvailable(Order)) {
                return new ResponseEntity("Booking was successful! Your Id: " + Order.getId(), HttpStatus.OK);
            }
            return new ResponseEntity("this time is not available", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("service not found", HttpStatus.BAD_REQUEST);
    }


    @ApiOperation(value = "Search a client with an ID", response = Order.class)
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
    public Order shoOrder(@PathVariable Integer id, Model model) {
        Order Order = orderService.getById(id);
        String timeLeft = orderService.calculate(Order.getTime());
        Order.setTimeLeft(timeLeft);
        return Order;
    }
}