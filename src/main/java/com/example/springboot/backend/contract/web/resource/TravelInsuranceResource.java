package com.example.springboot.backend.contract.web.resource;


import com.example.springboot.backend.contract.domain.PurposeOfTrip;
import com.example.springboot.backend.contract.domain.TravelInsurance;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TravelInsuranceResource extends LifeInsuranceResource
{
    protected boolean inEurope;
    @NotNull
    protected PurposeOfTrip purposeOfTrip;

    public TravelInsuranceResource(TravelInsurance travelInsurance)
    {
        super.idContract = travelInsurance.getIdContract();
        super.insurer = travelInsurance.getInsurer();
        super.startOfInsurance = travelInsurance.getStartOfInsurance();
        super.endOfInsurance = travelInsurance.getEndOfInsurance();
        super.amountOfInsuranceIndemnity = travelInsurance.getAmountOfInsuranceIndemnity();
        super.monthlyPayment = travelInsurance.getMonthlyPayment();
        super.insured = travelInsurance.getInsured();
        this.inEurope = travelInsurance.isInEurope();
        this.purposeOfTrip = travelInsurance.getPurposeOfTrip();
    }

    public TravelInsurance toTravelInsurance(Long id, LocalDateTime dateOfFormation)
    {
        return new TravelInsurance(id,dateOfFormation, super.insurer, super.startOfInsurance, super.endOfInsurance, super.amountOfInsuranceIndemnity, super.monthlyPayment, super.insured, this.inEurope, this.purposeOfTrip);
    }

}
