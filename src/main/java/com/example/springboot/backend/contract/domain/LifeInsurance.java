package com.example.springboot.backend.contract.domain;

import java.time.LocalDateTime;

public abstract class LifeInsurance extends Contract
{
    private Long insured;

    public LifeInsurance(long idContract, Long insurer, LocalDateTime startOfInsurance, LocalDateTime endOfInsurance, double amountOfInsuranceIndemnity, double monthlyPayment, Long insured)
    {
        super(idContract, insurer, startOfInsurance, endOfInsurance, amountOfInsuranceIndemnity, monthlyPayment);
        setInsured(insured);
    }

    public LifeInsurance(long idContract, LocalDateTime dateTimeOfFormation, Long insurer, LocalDateTime startOfInsurance, LocalDateTime endOfInsurance, double amountOfInsuranceIndemnity, double monthlyPayment, Long insured) {
        super(idContract, dateTimeOfFormation, insurer, startOfInsurance, endOfInsurance, amountOfInsuranceIndemnity, monthlyPayment);
        setInsured(insured);
    }

    public Long getInsured()
    {
        return insured;
    }

    private void setInsured(Long insured)
    {
        if(insured == null)
        {
            throw new IllegalArgumentException("Insured is required");
        }
        if(insured < 1)
        {
            throw new IllegalArgumentException("ID insurer must be greater than 0");
        }
        this.insured = insured;
    }

    @Override
    public String toString() {
        return super.toString() + "\n  LIFE INSURACE:  " + "  INSURED:  " + insured.toString();
    }
}
