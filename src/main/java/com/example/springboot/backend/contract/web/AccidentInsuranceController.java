package com.example.springboot.backend.contract.web;


import com.example.springboot.backend.InsuranceSystemService;
import com.example.springboot.backend.contract.domain.AccidentInsurance;
import com.example.springboot.backend.contract.domain.Contract;
import com.example.springboot.backend.contract.domain.TerritorialValidity;
import com.example.springboot.backend.contract.web.resource.AccidentInsuranceResource;
import com.example.springboot.backend.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/contract/accidentInsurance")
public class AccidentInsuranceController
{
    private final InsuranceSystemService insuranceSystemService;

    public AccidentInsuranceController(InsuranceSystemService insuranceSystemService) {
        this.insuranceSystemService = insuranceSystemService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity infoAccidentInsurance(@PathVariable long id)
    {
        Optional<Contract> contractOptional = this.insuranceSystemService.getContractService().findContractById(id);

        if(contractOptional.isPresent())
        {
            Contract contract = contractOptional.get();

            if(!(contract instanceof AccidentInsurance))
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            AccidentInsurance accidentInsurance = (AccidentInsurance) contract;
            return new ResponseEntity<>(new AccidentInsuranceResource(accidentInsurance), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity addAccidentInsuranceSubmit(@RequestBody AccidentInsuranceResource accidentInsuranceResource)
    {
        Contract contract = addContract(accidentInsuranceResource);
        return new ResponseEntity<>(contract, HttpStatus.CREATED);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity updateAccidentInsuranceSubmit(@PathVariable long id, @RequestBody AccidentInsuranceResource accidentInsuranceResource)
    {
        Optional<Contract> contractOptional = this.insuranceSystemService.getContractService().findContractById(id);

        if(contractOptional.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        Contract oldAccidentInsurance = contractOptional.get();

        if(!(oldAccidentInsurance instanceof AccidentInsurance))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        AccidentInsurance updatedAccidentInsurance = accidentInsuranceResource.toAccidentInsurance(oldAccidentInsurance.getIdContract(), oldAccidentInsurance.getDateTimeOfFormation());

        User oldInsurer = this.insuranceSystemService.getUserService().findUserById(oldAccidentInsurance.getInsurer()).get();
        User newInsurer = this.insuranceSystemService.getUserService().findUserById(updatedAccidentInsurance.getInsurer()).get();
        boolean correct = this.insuranceSystemService.getUpdateContractService().updateContract(updatedAccidentInsurance,oldInsurer,newInsurer);

        if(correct)
            return new ResponseEntity<>(new AccidentInsuranceResource(updatedAccidentInsurance), HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    private Contract addContract(AccidentInsuranceResource accidentInsuranceResource)
    {
        LocalDateTime start = accidentInsuranceResource.getStartOfInsurance();
        LocalDateTime end = accidentInsuranceResource.getEndOfInsurance();
        User insurer = this.insuranceSystemService.getUserService().findUserById(accidentInsuranceResource.getInsurer()).get();
        double amoutOfInsuranceIndemnity = accidentInsuranceResource.getAmountOfInsuranceIndemnity();
        double monthlyPayment = accidentInsuranceResource.getMonthlyPayment();
        User insured = this.insuranceSystemService.getUserService().findUserById(accidentInsuranceResource.getInsured()).get();
        double sumInsuredOfLastingConsequences = accidentInsuranceResource.getSumInsuredOfLastingConsequences();
        double sumInsuredOfDeath = accidentInsuranceResource.getSumInsuredOfDeath();
        double sumInsuredOfDailyCompensation = accidentInsuranceResource.getSumInsuredOfDailyCompensation();
        TerritorialValidity territorialValidity = accidentInsuranceResource.getTerritorialValidity();
        return this.insuranceSystemService.getContractService().registerAccidentInsurance(insurer,start,end,amoutOfInsuranceIndemnity,monthlyPayment,insured,sumInsuredOfLastingConsequences,sumInsuredOfDeath,sumInsuredOfDailyCompensation,territorialValidity);
    }
}
