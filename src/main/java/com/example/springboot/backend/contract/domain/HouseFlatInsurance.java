package com.example.springboot.backend.contract.domain;





import com.example.springboot.backend.address.Address;

import java.time.LocalDateTime;

public class HouseFlatInsurance extends NonLifeInsurance
{
    private boolean isGarageInsurance;

    public HouseFlatInsurance(long idContract, Long insurer, LocalDateTime startOfInsurance, LocalDateTime endOfInsurance, double amountOfInsuranceIndemnity, double monthlyPayment, PropertyType propertyType, Address address, double valueOfProperty, boolean isGarageInsurance) {
        super(idContract, insurer, startOfInsurance, endOfInsurance, amountOfInsuranceIndemnity, monthlyPayment, propertyType, address, valueOfProperty);
        setGarageInsurance(isGarageInsurance);
    }

    public HouseFlatInsurance(long idContract, LocalDateTime dateTimeOfFormation, Long insurer, LocalDateTime startOfInsurance, LocalDateTime endOfInsurance, double amountOfInsuranceIndemnity, double monthlyPayment, PropertyType propertyType, Address address, double valueOfProperty, boolean isGarageInsurance) {
        super(idContract, dateTimeOfFormation, insurer, startOfInsurance, endOfInsurance, amountOfInsuranceIndemnity, monthlyPayment, propertyType, address, valueOfProperty);
        setGarageInsurance(isGarageInsurance);
    }

    public boolean isGarageInsurance() {
        return isGarageInsurance;
    }

    public void setGarageInsurance(boolean garageInsurance) {
        isGarageInsurance = garageInsurance;
    }

    @Override
    public String toString() {
        return super.toString() + "\n    HOUSE AND FLAT INSURANCE  " +
                "  IS_GARAGE_INSURANCE:  " + isGarageInsurance;
    }
}
