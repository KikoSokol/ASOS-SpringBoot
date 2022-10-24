package com.example.springboot.backend.contract.domain;

import lombok.Getter;

@Getter
public enum PurposeOfTrip
{
    WORK(1,"Work"),
    HOLIDAYS(2,"Holidays"),
    SPORT(3,"Sport");

    private int number;
    private String name;

    PurposeOfTrip(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

