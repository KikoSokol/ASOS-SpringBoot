package com.example.springboot.backend.contract.domain;


import java.time.LocalDateTime;

public abstract class Contract
{
    private long idContract;
    private LocalDateTime dateTimeOfFormation;
    private Long insurer;
    private LocalDateTime startOfInsurance;
    private LocalDateTime endOfInsurance;
    private double amountOfInsuranceIndemnity;
    private double monthlyPayment;

    public Contract(long idContract, Long insurer, LocalDateTime startOfInsurance, LocalDateTime endOfInsurance, double amountOfInsuranceIndemnity, double monthlyPayment)
    {
        setIdContract(idContract);
        setDateTimeOfFormation();
        setInsurer(insurer);
        setStartOfInsurance(startOfInsurance);
        setEndOfInsurance(endOfInsurance);
        setAmountOfInsuranceIndemnity(amountOfInsuranceIndemnity);
        setMonthlyPayment(monthlyPayment);
    }

    public Contract(long idContract, LocalDateTime dateTimeOfFormation, Long insurer, LocalDateTime startOfInsurance, LocalDateTime endOfInsurance, double amountOfInsuranceIndemnity, double monthlyPayment)
    {
        setIdContract(idContract);
        this.dateTimeOfFormation = dateTimeOfFormation;
        setInsurer(insurer);
        setStartOfInsurance(startOfInsurance);
        setEndOfInsurance(endOfInsurance);
        setAmountOfInsuranceIndemnity(amountOfInsuranceIndemnity);
        setMonthlyPayment(monthlyPayment);
    }

    public long getIdContract() {
        return idContract;
    }

    private void setIdContract(long idContract)
    {
        if(idContract < 1)
        {
            throw new IllegalArgumentException("Id contract must be greater than 0");
        }
        this.idContract = idContract;
    }

    public LocalDateTime getDateTimeOfFormation() {
        return dateTimeOfFormation;
    }

    private void setDateTimeOfFormation()
    {
        this.dateTimeOfFormation = LocalDateTime.now();
    }

    public Long getInsurer() {
        return insurer;
    }

    public void setInsurer(Long insurer)
    {
        if(insurer == null)
        {
            throw new IllegalArgumentException("Insurer is required");
        }
        if(insurer < 1)
        {
            throw new IllegalArgumentException("ID insurer must be greater than 0");
        }
        this.insurer = insurer;
    }

    public LocalDateTime getStartOfInsurance() {
        return startOfInsurance;
    }

    public void setStartOfInsurance(LocalDateTime startOfInsurance)
    {
        if(startOfInsurance == null)
        {
            throw new IllegalArgumentException("Date and time of start of insurance is required");
        }
        this.startOfInsurance = startOfInsurance;
    }

    public LocalDateTime getEndOfInsurance() {
        return endOfInsurance;
    }

    public void setEndOfInsurance(LocalDateTime endOfInsurance)
    {
        if(endOfInsurance == null)
        {
            throw new IllegalArgumentException("Date and time of end of insurance is required");
        }
        this.endOfInsurance = endOfInsurance;
    }

    public double getAmountOfInsuranceIndemnity() {
        return amountOfInsuranceIndemnity;
    }

    public void setAmountOfInsuranceIndemnity(double amountOfInsuranceIndemnity)
    {
        if(amountOfInsuranceIndemnity < 0)
        {
            throw new IllegalArgumentException("Amount of insurance indemnity must be 0 or greater than 0");
        }
        this.amountOfInsuranceIndemnity = amountOfInsuranceIndemnity;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment)
    {
        if(monthlyPayment < 0)
        {
            throw new IllegalArgumentException("Monthly payment must be 0 or greater than 0");
        }
        this.monthlyPayment = monthlyPayment;
    }

    @Override
    public String toString() {
        return  "CONTRACT:  " + "  ID_CONTRACT:  " + idContract +
                "  TIME_OF_FORMATION:  " + dateTimeOfFormation +
                "  INSURER:  " + insurer +
                "  START_OF_INSURANCE:  " + startOfInsurance +
                "  END_OF_INSURANCE:" + endOfInsurance +
                "  AMOUNT_OF_INSURANCE_INDEMNITY:  " + amountOfInsuranceIndemnity +
                "  MONTHLY_PAYMENT:  " + monthlyPayment;
    }
}

