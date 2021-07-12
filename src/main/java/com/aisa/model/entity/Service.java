package com.aisa.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "Services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int price;

    private String service;

    public void setPrice(int price) {
        this.price = price;
    }


    public Service() {
    }

    public void setService_name(String service) {
        this.service = service;
    }

    public int getPrice() {
        return price;
    }

    public String getService_name() {
        return service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
