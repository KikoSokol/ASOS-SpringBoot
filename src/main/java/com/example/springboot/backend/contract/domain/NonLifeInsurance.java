package com.example.springboot.backend.contract.domain;





import com.example.springboot.backend.address.Address;

import java.time.LocalDateTime;

public abstract class NonLifeInsurance extends Contract
{
    private PropertyType propertyType;
    private Address address;
    private double valueOfProperty;

    public NonLifeInsurance(long idContract, Long insurer, LocalDateTime startOfInsurance, LocalDateTime endOfInsurance, double amountOfInsuranceIndemnity, double monthlyPayment, PropertyType propertyType, Address address, double valueOfProperty)
    {
        super(idContract, insurer, startOfInsurance, endOfInsurance, amountOfInsuranceIndemnity, monthlyPayment);
        setPropertyType(propertyType);
        setAddress(address);
        setValueOfProperty(valueOfProperty);
    }

    public NonLifeInsurance(long idContract, LocalDateTime dateTimeOfFormation, Long insurer, LocalDateTime startOfInsurance, LocalDateTime endOfInsurance, double amountOfInsuranceIndemnity, double monthlyPayment, PropertyType propertyType, Address address, double valueOfProperty) {
        super(idContract, dateTimeOfFormation, insurer, startOfInsurance, endOfInsurance, amountOfInsuranceIndemnity, monthlyPayment);
        setPropertyType(propertyType);
        setAddress(address);
        setValueOfProperty(valueOfProperty);
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    private void setPropertyType(PropertyType propertyType)
    {
        if(propertyType == null)
        {
            throw new IllegalArgumentException("Property type is required");
        }
        this.propertyType = propertyType;
    }

    public Address getAddress() {
        return address;
    }

    private void setAddress(Address address)
    {
        if(address == null)
        {
            throw new IllegalArgumentException("Address is required");
        }
        this.address = address;
    }

    public double getValueOfProperty() {
        return valueOfProperty;
    }

    public void setValueOfProperty(double valueOfProperty)
    {
        if(valueOfProperty < 0)
        {
            throw new IllegalArgumentException("Value of property must not be less than 0");
        }
        this.valueOfProperty = valueOfProperty;
    }

    @Override
    public String toString() {
        return super.toString() + "\n  NON LIFE INSURANCE  " +
                "  PROPERTY_TYPE:  " + propertyType.toString() +
                "  ADDRESS:  " + address.toString() +
                "  VALUE_OF_PROPERTY:  " + valueOfProperty;
    }
}
