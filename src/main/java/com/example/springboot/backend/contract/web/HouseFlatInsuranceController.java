package com.example.springboot.backend.contract.web;


import com.example.springboot.backend.InsuranceSystemService;
import com.example.springboot.backend.address.Address;
import com.example.springboot.backend.address.attribute.PostalCodeParseException;
import com.example.springboot.backend.contract.domain.Contract;
import com.example.springboot.backend.contract.domain.HouseFlatInsurance;
import com.example.springboot.backend.contract.domain.PropertyType;
import com.example.springboot.backend.contract.web.resource.HouseFlatInsuranceResource;
import com.example.springboot.backend.contract.web.resource.HouseholdInsuranceResource;
import com.example.springboot.backend.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/contract/houseFlatInsurance")
public class HouseFlatInsuranceController
{
    private final InsuranceSystemService insuranceSystemService;

    public HouseFlatInsuranceController(InsuranceSystemService insuranceSystemService) {
        this.insuranceSystemService = insuranceSystemService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity infoHouseFlatInsurance(@PathVariable long id)
    {
        Optional<Contract> contractOptional = this.insuranceSystemService.getContractService().findContractById(id);

        if(contractOptional.isPresent())
        {
            Contract contract = contractOptional.get();

            if(!(contract instanceof HouseFlatInsurance))
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            HouseFlatInsurance houseFlatInsurance = (HouseFlatInsurance) contract;
            return new ResponseEntity<>(new HouseFlatInsuranceResource(houseFlatInsurance), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity addHouseFlatInsuranceSubmit(@RequestBody HouseFlatInsuranceResource houseFlatInsuranceResource)
    {
        Contract contract = addContract(houseFlatInsuranceResource);

        if(contract == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);


        return new ResponseEntity<>(contract, HttpStatus.CREATED);
    }



    @PutMapping("/update/submit/id/{id}")
    public ResponseEntity updateHouseFlatInsuranceSubmit(@PathVariable long id, @RequestBody HouseFlatInsuranceResource houseFlatInsuranceResource)
    {
        String checkDate = checkDate(houseFlatInsuranceResource.getStartOfInsurance(),houseFlatInsuranceResource.getEndOfInsurance());
        if(!checkDate.equals(""))
        {
            return new ResponseEntity<>(checkDate, HttpStatus.BAD_REQUEST);
        }

        Optional<Contract> contractOptional = this.insuranceSystemService.getContractService().findContractById(id);

        if(contractOptional.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        Contract oldHouseFlatInsurance = contractOptional.get();

        if(!(oldHouseFlatInsurance instanceof HouseFlatInsurance))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        try
        {
            System.out.println(((HouseFlatInsurance) oldHouseFlatInsurance).getAddress().getPostalCode().getPostalCode());
            System.out.println(houseFlatInsuranceResource.getPostalCode());
            HouseFlatInsurance updatedHouseFlatInsurance = houseFlatInsuranceResource.toHouseFlatInsurance(oldHouseFlatInsurance.getIdContract(), oldHouseFlatInsurance.getDateTimeOfFormation());
            User oldInsurer = this.insuranceSystemService.getUserService().findUserById(oldHouseFlatInsurance .getInsurer()).get();
            User newInsurer = this.insuranceSystemService.getUserService().findUserById(updatedHouseFlatInsurance.getInsurer()).get();
            boolean correct = this.insuranceSystemService.getUpdateContractService().updateContract(updatedHouseFlatInsurance,oldInsurer,newInsurer);

            if(correct)
                return new ResponseEntity<>(new HouseFlatInsuranceResource(updatedHouseFlatInsurance), HttpStatus.OK);

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
            return "ate of start insurance must be before date of end insurance";
        }
        return "";
    }

    private Contract addContract(HouseFlatInsuranceResource houseFlatInsuranceResource)
    {
        try
        {
            LocalDateTime start = houseFlatInsuranceResource.getStartOfInsurance();
            LocalDateTime end = houseFlatInsuranceResource.getEndOfInsurance();
            User insurer = this.insuranceSystemService.getUserService().findUserById(houseFlatInsuranceResource.getInsurer()).get();
            double amoutOfInsuranceIndemnity = houseFlatInsuranceResource.getAmountOfInsuranceIndemnity();
            double monthlyPayment = houseFlatInsuranceResource.getMonthlyPayment();
            PropertyType propertyType = houseFlatInsuranceResource.getPropertyType();
            Address address = houseFlatInsuranceResource.getAddress();
            double valueOfProperty = houseFlatInsuranceResource.getValueOfProperty();
            boolean idGarageInsurance = houseFlatInsuranceResource.isGarageInsurance();
            return this.insuranceSystemService.getContractService().registerHouseFlatInsurance(insurer,start,end,amoutOfInsuranceIndemnity,monthlyPayment,propertyType,address,valueOfProperty,idGarageInsurance);
        }
        catch (PostalCodeParseException e)
        {
            return null;
        }
    }
}
