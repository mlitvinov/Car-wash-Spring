package com.aisa.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String tel;
    private String service;
    private String time;
    private String timeLeft;


    public void setTimeLeft(String timeLef) {
        this.timeLeft = timeLef;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public Order() {

    }

    public Order(String tel, String service, String time) {
        this.tel = tel;
        this.service = service;
        this.time = time;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTel() {
        return tel;
    }

    public String getService() {
        return service;
    }

    public String getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
