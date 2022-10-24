package com.example.springboot.backend.contract.web;


import com.example.springboot.backend.InsuranceSystemService;
import com.example.springboot.backend.address.Address;
import com.example.springboot.backend.address.attribute.PostalCodeParseException;
import com.example.springboot.backend.contract.domain.Contract;
import com.example.springboot.backend.contract.domain.HouseholdInsurance;
import com.example.springboot.backend.contract.domain.PropertyType;
import com.example.springboot.backend.contract.web.resource.HouseholdInsuranceResource;
import com.example.springboot.backend.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/contract/householdInsurance")
public class HouseholdInsuranceController
{
    private final InsuranceSystemService insuranceSystemService;

    public HouseholdInsuranceController(InsuranceSystemService insuranceSystemService) {
        this.insuranceSystemService = insuranceSystemService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity infoHouseholdInsurance(@PathVariable long id)
    {
        Optional<Contract> contractOptional = this.insuranceSystemService.getContractService().findContractById(id);

        if(contractOptional.isPresent())
        {
            Contract contract = contractOptional.get();

            if(!(contract instanceof HouseholdInsurance))
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            HouseholdInsurance householdInsurance = (HouseholdInsurance) contract;
            return new ResponseEntity<>(new HouseholdInsuranceResource(householdInsurance), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity addHouseholdInsuranceSubmit(@RequestBody HouseholdInsuranceResource householdInsuranceResource)
    {
        String checkDate = checkDate(householdInsuranceResource.getStartOfInsurance(),householdInsuranceResource.getEndOfInsurance());

        if(!checkDate.equals(""))
            return new ResponseEntity<>(checkDate, HttpStatus.BAD_REQUEST);

        Contract contract = addContract(householdInsuranceResource);

        if(contract == null)
        {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(contract, HttpStatus.CREATED);
    }

    @PutMapping("/update/submit/id/{id}")
    public ResponseEntity updateHouseholdInsuranceSubmit(@PathVariable long id, @RequestBody HouseholdInsuranceResource householdInsuranceResource)
    {

        String checkDate = checkDate(householdInsuranceResource.getStartOfInsurance(),householdInsuranceResource.getEndOfInsurance());

        if(!checkDate.equals(""))
            return new ResponseEntity<>(checkDate, HttpStatus.BAD_REQUEST);


        Optional<Contract> optionalContract = this.insuranceSystemService.getContractService().findContractById(id);

        if(optionalContract.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        Contract oldHouseholdInsurance = optionalContract.get();

        if(!(oldHouseholdInsurance instanceof HouseholdInsurance))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        try
        {
            HouseholdInsurance updatedHouseholdInsurance = householdInsuranceResource.toHouseholdInsurance(oldHouseholdInsurance.getIdContract(), oldHouseholdInsurance.getDateTimeOfFormation());
            User oldInsurer = this.insuranceSystemService.getUserService().findUserById(oldHouseholdInsurance .getInsurer()).get();
            User newInsurer = this.insuranceSystemService.getUserService().findUserById(updatedHouseholdInsurance.getInsurer()).get();
            boolean correct = this.insuranceSystemService.getUpdateContractService().updateContract(updatedHouseholdInsurance,oldInsurer,newInsurer);

            if(correct)
                return new ResponseEntity<>(new HouseholdInsuranceResource(updatedHouseholdInsurance), HttpStatus.OK);

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        catch (PostalCodeParseException e)
        {
            return new ResponseEntity<>(checkDate, HttpStatus.BAD_REQUEST);
        }
    }

    private String checkDate(LocalDateTime start, LocalDateTime end)
    {
        if(start != null && end != null && start.isAfter(end))
        {
            return "Date of start insurance must be before date of end insurance";
        }

        return "";
    }

    private Contract addContract(HouseholdInsuranceResource householdInsuranceResource)
    {
        try
        {
            LocalDateTime start = householdInsuranceResource.getStartOfInsurance();
            LocalDateTime end = householdInsuranceResource.getEndOfInsurance();
            System.out.println(householdInsuranceResource.getInsurer());
            User insurer = this.insuranceSystemService.getUserService().findUserById(householdInsuranceResource.getInsurer()).get();
            double amoutOfInsuranceIndemnity = householdInsuranceResource.getAmountOfInsuranceIndemnity();
            double monthlyPayment = householdInsuranceResource.getMonthlyPayment();
            PropertyType propertyType = householdInsuranceResource.getPropertyType();
            Address address = householdInsuranceResource.getAddress();
            double valueOfProperty = householdInsuranceResource.getValueOfProperty();
            double valueOfHouseholdAppliances = householdInsuranceResource.getValueOfHouseholdAppliances();
            return this.insuranceSystemService.getContractService().registerHouseHoldInsurance(insurer,start,end,amoutOfInsuranceIndemnity,monthlyPayment,propertyType,address,valueOfProperty,valueOfHouseholdAppliances);
        }
        catch (PostalCodeParseException e)
        {
            return null;
        }

    }
}
