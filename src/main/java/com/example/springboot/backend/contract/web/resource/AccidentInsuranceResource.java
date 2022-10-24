package com.example.springboot.backend.contract.web.resource;


import com.example.springboot.backend.contract.domain.AccidentInsurance;
import com.example.springboot.backend.contract.domain.TerritorialValidity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AccidentInsuranceResource extends LifeInsuranceResource
{
    @Positive
    protected double sumInsuredOfLastingConsequences;
    @Positive
    protected double sumInsuredOfDeath;
    @Positive
    protected double sumInsuredOfDailyCompensation;
    @NotNull
    protected TerritorialValidity territorialValidity;

    public AccidentInsuranceResource(AccidentInsurance accidentInsurance)
    {
        super.idContract = accidentInsurance.getIdContract();
        super.insurer = accidentInsurance.getInsurer();
        super.startOfInsurance = accidentInsurance.getStartOfInsurance();
        super.endOfInsurance = accidentInsurance.getEndOfInsurance();
        super.amountOfInsuranceIndemnity = accidentInsurance.getAmountOfInsuranceIndemnity();
        super.monthlyPayment = accidentInsurance.getMonthlyPayment();
        super.insured = accidentInsurance.getInsured();
        this.sumInsuredOfLastingConsequences = accidentInsurance.getSumInsuredOfLastingConsequences();
        this.sumInsuredOfDeath = accidentInsurance.getSumInsuredOfDeath();
        this.sumInsuredOfDailyCompensation = accidentInsurance.getSumInsuredOfDailyCompensation();
        this.territorialValidity = accidentInsurance.getTerritorialValidity();
    }

    public AccidentInsurance toAccidentInsurance(Long id, LocalDateTime dateOfFormation)
    {
        return new AccidentInsurance(id,dateOfFormation,super.insurer,super.startOfInsurance,super.getEndOfInsurance(),super.amountOfInsuranceIndemnity,super.monthlyPayment,super.insured,this.sumInsuredOfLastingConsequences,this.sumInsuredOfDeath,this.sumInsuredOfDailyCompensation,this.territorialValidity);
    }

}
