package com.example.springboot.backend.contract.domain;

import java.time.LocalDateTime;

public class AccidentInsurance extends LifeInsurance
{
    private double sumInsuredOfLastingConsequences;
    private double sumInsuredOfDeath;
    private double sumInsuredOfDailyCompensation;
    private TerritorialValidity territorialValidity;

    public AccidentInsurance(long idContract, Long insurer, LocalDateTime startOfInsurance, LocalDateTime endOfInsurance, double amountOfInsuranceIndemnity, double monthlyPayment, Long insured, double sumInsuredOfLastingConsequences, double sumInsuredOfDeath, double sumInsuredOfDailyCompensation, TerritorialValidity territorialValidity) {
        super(idContract, insurer, startOfInsurance, endOfInsurance, amountOfInsuranceIndemnity, monthlyPayment, insured);
        setSumInsuredOfLastingConsequences(sumInsuredOfLastingConsequences);
        setSumInsuredOfDeath(sumInsuredOfDeath);
        setSumInsuredOfDailyCompensation(sumInsuredOfDailyCompensation);
        setTerritorialValidity(territorialValidity);
    }

    public AccidentInsurance(long idContract, LocalDateTime dateTimeOfFormation, Long insurer, LocalDateTime startOfInsurance, LocalDateTime endOfInsurance, double amountOfInsuranceIndemnity, double monthlyPayment, Long insured, double sumInsuredOfLastingConsequences, double sumInsuredOfDeath, double sumInsuredOfDailyCompensation, TerritorialValidity territorialValidity) {
        super(idContract, dateTimeOfFormation, insurer, startOfInsurance, endOfInsurance, amountOfInsuranceIndemnity, monthlyPayment, insured);
        setSumInsuredOfLastingConsequences(sumInsuredOfLastingConsequences);
        setSumInsuredOfDeath(sumInsuredOfDeath);
        setSumInsuredOfDailyCompensation(sumInsuredOfDailyCompensation);
        setTerritorialValidity(territorialValidity);
    }

    public double getSumInsuredOfLastingConsequences() {
        return sumInsuredOfLastingConsequences;
    }

    public void setSumInsuredOfLastingConsequences(double sumInsuredOfLastingConsequences)
    {
        if( sumInsuredOfLastingConsequences < 0)
        {
            throw new IllegalArgumentException("Sum insured of lasting consequences must not be less than 0");
        }
        this.sumInsuredOfLastingConsequences = sumInsuredOfLastingConsequences;
    }

    public double getSumInsuredOfDeath() {
        return sumInsuredOfDeath;
    }

    public void setSumInsuredOfDeath(double sumInsuredOfDeath)
    {
        if(sumInsuredOfDeath < 0)
        {
            throw new IllegalArgumentException("Sum insured of death must not be less than 0");
        }
        this.sumInsuredOfDeath = sumInsuredOfDeath;
    }

    public double getSumInsuredOfDailyCompensation() {
        return sumInsuredOfDailyCompensation;
    }

    public void setSumInsuredOfDailyCompensation(double sumInsuredOfDailyCompensation)
    {
        if(sumInsuredOfDailyCompensation < 0)
        {
            throw new IllegalArgumentException("Sum insured of daily compensation must not be less than 0");
        }
        this.sumInsuredOfDailyCompensation = sumInsuredOfDailyCompensation;
    }

    public TerritorialValidity getTerritorialValidity() {
        return territorialValidity;
    }

    private void setTerritorialValidity(TerritorialValidity territorialValidity)
    {
        if(territorialValidity == null)
        {
            throw new IllegalArgumentException("Territoral validity is required");
        }
        this.territorialValidity = territorialValidity;
    }

    @Override
    public String toString()
    {
        return  super.toString() + "\n    ACCIDENT INSURANCE:  " +
                "  SUM_INSURED_OF_LASTING_CONSEQUENCES:  " + sumInsuredOfLastingConsequences +
                "  SUM_INSURED_OF_DEATH:  " + sumInsuredOfDeath +
                "  SUM_INSURED_OF_DAILY_COMPENSATION:  " + sumInsuredOfDailyCompensation +
                "  TERRITORIAL_VALIDITY:  " + territorialValidity.getName();
    }
}

