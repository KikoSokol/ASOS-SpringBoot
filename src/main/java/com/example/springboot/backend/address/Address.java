package com.example.springboot.backend.address;


import com.example.springboot.backend.address.attribute.PostalCode;

public class Address
{
    private String nameOfCity;
    private String nameOfStreet;
    private PostalCode postalCode;
    private int numberOfHouse;

    public Address(String nameOfCity, String nameOfStreet, PostalCode postalCode, int numberOfHouse)
    {
        setNameOfCity(nameOfCity);
        setNameOfStreet(nameOfStreet);
        setPostalCode(postalCode);
        setNumberOfHouse(numberOfHouse);
    }

    public String getNameOfCity() {
        return nameOfCity;
    }

    public void setNameOfCity(String nameOfCity)
    {
        if(nameOfCity == null || nameOfCity.trim().isEmpty())
        {
            throw new IllegalArgumentException("Name of city is required");
        }
        this.nameOfCity = nameOfCity;
    }

    public String getNameOfStreet() {
        return nameOfStreet;
    }

    public void setNameOfStreet(String nameOfStreet)
    {
        if(nameOfStreet == null || nameOfStreet.trim().isEmpty())
        {
            throw new IllegalArgumentException("Name of Street is required");
        }
        this.nameOfStreet = nameOfStreet;
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(PostalCode postalCode)
    {
        if(postalCode == null)
        {
            throw new IllegalArgumentException("Postal code id required");
        }
        this.postalCode = postalCode;
    }

    public int getNumberOfHouse() {
        return numberOfHouse;
    }

    public void setNumberOfHouse(int numberOfHouse)
    {
        if(numberOfHouse < 1)
        {
            throw new IllegalArgumentException("Bad number of house. Number must be greater than 0");
        }
        this.numberOfHouse = numberOfHouse;
    }

    @Override
    public String toString() {
        return nameOfStreet + " " + numberOfHouse + ", " + postalCode.getPostalCode() + " " + nameOfCity;
    }
}
