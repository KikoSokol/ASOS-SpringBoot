package com.example.springboot.backend.contract.domain;





import com.example.springboot.backend.address.Address;

import java.time.LocalDateTime;

public class HouseholdInsurance extends NonLifeInsurance
{
    private double valueOfHouseholdAppliances;

    public HouseholdInsurance(long idContract, Long insurer, LocalDateTime startOfInsurance, LocalDateTime endOfInsurance, double amountOfInsuranceIndemnity, double monthlyPayment, PropertyType propertyType, Address address, double valueOfProperty, double valueOfHouseholdAppliances) {
        super(idContract, insurer, startOfInsurance, endOfInsurance, amountOfInsuranceIndemnity, monthlyPayment, propertyType, address, valueOfProperty);
        setValueOfHouseholdAppliances(valueOfHouseholdAppliances);
    }

    public HouseholdInsurance(long idContract, LocalDateTime dateTimeOfFormation, Long insurer, LocalDateTime startOfInsurance, LocalDateTime endOfInsurance, double amountOfInsuranceIndemnity, double monthlyPayment, PropertyType propertyType, Address address, double valueOfProperty, double valueOfHouseholdAppliances) {
        super(idContract, dateTimeOfFormation, insurer, startOfInsurance, endOfInsurance, amountOfInsuranceIndemnity, monthlyPayment, propertyType, address, valueOfProperty);
        setValueOfHouseholdAppliances(valueOfHouseholdAppliances);
    }

    public double getValueOfHouseholdAppliences() {
        return valueOfHouseholdAppliances;
    }

    public void setValueOfHouseholdAppliances(double valueOfHouseholdAppliances)
    {
        if(valueOfHouseholdAppliances < 0)
        {
            throw new IllegalArgumentException("Value of household appliances must not be less than 0");
        }
        this.valueOfHouseholdAppliances = valueOfHouseholdAppliances;
    }

    @Override
    public String toString()
    {
        return super.toString() + "\n    HOUSEHOLD INSURANCE  " +
                "  VALUE_OF_HOUSEHOLD_APPLIANCES:  " + valueOfHouseholdAppliances;
    }
}
