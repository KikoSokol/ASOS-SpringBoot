package com.example.springboot.backend.contract.domain;

public enum  PropertyType
{
    FLAT(1, "Flat"),
    BRICK_FAMILY_HOUSE(2, "Brick family house"),
    WOODEN_FAMILY_HOUSE(3, "Wooden family house");

    private int number;
    private String name;

    PropertyType(int number, String name) {
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
