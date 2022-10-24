package com.example.springboot.backend.contract.web;


import com.example.springboot.backend.InsuranceSystemService;
import com.example.springboot.backend.contract.domain.Contract;
import com.example.springboot.backend.contract.domain.PurposeOfTrip;
import com.example.springboot.backend.contract.domain.TravelInsurance;
import com.example.springboot.backend.contract.web.resource.TravelInsuranceResource;
import com.example.springboot.backend.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/contract/travelInsurance")
public class TravelInsuranceController
{
    private final InsuranceSystemService insuranceSystemService;

    public TravelInsuranceController(InsuranceSystemService insuranceSystemService) {
        this.insuranceSystemService = insuranceSystemService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity infoTravelInsurance(@PathVariable long id)
    {
        Optional<Contract> contractOptional = this.insuranceSystemService.getContractService().findContractById(id);

        if(contractOptional.isPresent())
        {
            Contract contract = contractOptional.get();

            if(!(contract instanceof TravelInsurance))
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            TravelInsurance travelInsurance = (TravelInsurance) contract;
            return new ResponseEntity<>(new TravelInsuranceResource(travelInsurance), HttpStatus.OK);

        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity addTravelInsuranceSubmit(@RequestBody TravelInsuranceResource travelInsuranceResource)
    {
        String checkDate = checkDate(travelInsuranceResource.getStartOfInsurance(),travelInsuranceResource.getEndOfInsurance());
        if(!checkDate.equals(""))
            return new ResponseEntity<>(checkDate, HttpStatus.BAD_REQUEST);

        Contract contract = addContract(travelInsuranceResource);

        return new ResponseEntity<>(contract, HttpStatus.CREATED);
    }

    @PutMapping("/update/submit/id/{id}")
    public ResponseEntity updateTravelInsuranceSubmit(@PathVariable long id, @RequestBody TravelInsuranceResource travelInsuranceResource)
    {

        String checkDate = checkDate(travelInsuranceResource.getStartOfInsurance(),travelInsuranceResource.getEndOfInsurance());

        if(!checkDate.equals(""))
            return new ResponseEntity<>(checkDate, HttpStatus.BAD_REQUEST);


        Optional<Contract> contractOptional = this.insuranceSystemService.getContractService().findContractById(id);

        if(contractOptional.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);


        Contract oldTravelInsurance = contractOptional.get();

        if(!(oldTravelInsurance instanceof TravelInsurance))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        TravelInsurance updatedTravelInsurance = travelInsuranceResource.toTravelInsurance(oldTravelInsurance.getIdContract(), oldTravelInsurance.getDateTimeOfFormation());
        User oldInsurer = this.insuranceSystemService.getUserService().findUserById(oldTravelInsurance.getInsurer()).get();
        User newInsurer = this.insuranceSystemService.getUserService().findUserById(updatedTravelInsurance.getInsurer()).get();
        boolean correct = this.insuranceSystemService.getUpdateContractService().updateContract(updatedTravelInsurance,oldInsurer,newInsurer);

        if(correct)
            return new ResponseEntity<>(new TravelInsuranceResource(updatedTravelInsurance), HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    private String checkDate(LocalDateTime start, LocalDateTime end)
    {
        if(start != null && end != null && start.isAfter(end))
        {
            return "Date of start insurance must be before date of end insurance";
        }

        return "";
    }

    private Contract addContract(TravelInsuranceResource travelInsuranceResource)
    {
        LocalDateTime start = travelInsuranceResource.getStartOfInsurance();
        LocalDateTime end = travelInsuranceResource.getEndOfInsurance();
        User insurer = this.insuranceSystemService.getUserService().findUserById(travelInsuranceResource.getInsurer()).get();
        double amoutOfInsuranceIndemnity = travelInsuranceResource.getAmountOfInsuranceIndemnity();
        double monthlyPayment = travelInsuranceResource.getMonthlyPayment();
        User insured = this.insuranceSystemService.getUserService().findUserById(travelInsuranceResource.getInsured()).get();
        boolean inEurope = travelInsuranceResource.isInEurope();
        PurposeOfTrip purposeOfTrip2 = travelInsuranceResource.getPurposeOfTrip();
        return this.insuranceSystemService.getContractService().registerTravelInsurance(insurer,start,end,amoutOfInsuranceIndemnity,monthlyPayment,insured,inEurope,purposeOfTrip2);
    }
}
