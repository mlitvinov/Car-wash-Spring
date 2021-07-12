package com.aisa.controllers;

import com.aisa.model.entity.Service;
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
@RequestMapping("/services")
@Api(value = "services", description = "Operations related to car wash services")
public class ServiceController {

    private ServiceService serviceService;

    @Autowired
    public void setServiceListService(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @ApiOperation(value = "View a list of available services", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public Iterable<Service> list(Model model) {
        return serviceService.listAll();
    }

    @ApiOperation(value = "Search a services with an ID", response = Service.class)
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
    public Service showService(@PathVariable Integer id, Model model) {
        return serviceService.getById(id);
    }

    @ApiOperation(value = "Add a service")
    @RequestMapping(value = "/manage/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveService(@RequestBody Service service) {
        serviceService.save(service);
        return new ResponseEntity("Service saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a service")
    @RequestMapping(value = "/manage/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer id) {
        serviceService.delete(id);
        return new ResponseEntity("Service deleted successfully", HttpStatus.OK);
    }
}
