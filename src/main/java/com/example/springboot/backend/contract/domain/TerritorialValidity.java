package com.example.springboot.backend.contract.domain;

public enum TerritorialValidity
{
    SLOVAKIA(1,"Slovakia"),
    WORLD(2,"World"),
    SLOVAKIA_PLUS_WORLD(3,"Slovakia + World");

    private int number;
    private String name;


    TerritorialValidity(int number,String name) {
        this.name = name;
        this.number = number;
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
