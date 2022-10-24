package com.example.springboot.backend.contract.domain;


import java.time.LocalDateTime;

public class TravelInsurance extends LifeInsurance
{
    private boolean inEurope;
    private PurposeOfTrip purposeOfTrip;

    public TravelInsurance(long idContract, Long insurer, LocalDateTime startOfInsurance, LocalDateTime endOfInsurance, double amountOfInsuranceIndemnity, double monthlyPayment, Long insured, boolean inEurope, PurposeOfTrip purposeOfTrip) {
        super(idContract, insurer, startOfInsurance, endOfInsurance, amountOfInsuranceIndemnity, monthlyPayment, insured);
        setInEurope(inEurope);
        setPurposeOfTrip(purposeOfTrip);
    }

    public TravelInsurance(long idContract, LocalDateTime dateTimeOfFormation, Long insurer, LocalDateTime startOfInsurance, LocalDateTime endOfInsurance, double amountOfInsuranceIndemnity, double monthlyPayment, Long insured, boolean inEurope, PurposeOfTrip purposeOfTrip) {
        super(idContract, dateTimeOfFormation, insurer, startOfInsurance, endOfInsurance, amountOfInsuranceIndemnity, monthlyPayment, insured);
        setInEurope(inEurope);
        setPurposeOfTrip(purposeOfTrip);
    }

    public boolean isInEurope() {
        return inEurope;
    }

    public void setInEurope(boolean inEurope) {
        this.inEurope = inEurope;
    }

    public PurposeOfTrip getPurposeOfTrip() {
        return purposeOfTrip;
    }

    public void setPurposeOfTrip(PurposeOfTrip purposeOfTrip)
    {
        if(purposeOfTrip == null)
        {
            throw new IllegalArgumentException("Purpose of trip is required");
        }
        this.purposeOfTrip = purposeOfTrip;
    }

    @Override
    public String toString() {
        return super.toString() + "\n    TRAVEL INSURANCE:  " +
                "  IN_EUROPE:  " + inEurope +
                "  PURPOSE_OF_TRIP:  " + purposeOfTrip.toString();
    }
}
