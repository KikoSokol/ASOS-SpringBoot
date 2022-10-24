package com.example.springboot.backend.contract.service;


import com.example.springboot.backend.address.Address;
import com.example.springboot.backend.contract.domain.*;
import com.example.springboot.backend.user.domain.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BasicContractService implements ContractService, UpdateContractService, InformationContractService
{
    private Map<Long, Contract> contracts;
    private AtomicLong idCounter;

    public BasicContractService()
    {
        contracts = new HashMap<>();
        idCounter = new AtomicLong(1);
    }

    public Contract registerTravelInsurance(User insurer, LocalDateTime start, LocalDateTime end, double amountOfInsuranceIndemnity, double monthlyPayment, User insured, boolean inEurope, PurposeOfTrip purposeOfTrip)
    {
        long newId = idCounter.get();
        TravelInsurance travelInsurance = new TravelInsurance(newId,insurer.getIdUser(),start,end,amountOfInsuranceIndemnity,monthlyPayment,insured.getIdUser(),inEurope,purposeOfTrip);
        this.contracts.put(newId,travelInsurance);
        this.idCounter.incrementAndGet();
        insurer.addContractForUser(travelInsurance.getIdContract());
        return travelInsurance;
    }

    public Contract registerAccidentInsurance(User insurer, LocalDateTime startOfInsurance, LocalDateTime endOfInsurance, double amountOfInsuranceIndemnity, double monthlyPayment, User insured, double sumInsuredOfLastingConsequences, double sumInsuredOfDeath, double sumInsuredOfDailyCompensation, TerritorialValidity territorialValidity)
    {
        long newId = idCounter.get();
        AccidentInsurance accidentInsurance = new AccidentInsurance(newId,insurer.getIdUser(),startOfInsurance,endOfInsurance,amountOfInsuranceIndemnity,monthlyPayment,insured.getIdUser(),sumInsuredOfLastingConsequences,sumInsuredOfDeath,sumInsuredOfDailyCompensation,territorialValidity);
        this.contracts.put(newId,accidentInsurance);
        this.idCounter.incrementAndGet();
        insurer.addContractForUser(accidentInsurance.getIdContract());
        return accidentInsurance;
    }

    public Contract registerHouseFlatInsurance(User insurer, LocalDateTime startOfInsurance, LocalDateTime endOfInsurance, double amountOfInsuranceIndemnity, double monthlyPayment, PropertyType propertyType, Address address, double valueOfProperty, boolean isGarageInsurance)
    {
        long newId = idCounter.get();
        HouseFlatInsurance houseFlatInsurance = new HouseFlatInsurance(newId,insurer.getIdUser(),startOfInsurance,endOfInsurance,amountOfInsuranceIndemnity,monthlyPayment,propertyType,address,valueOfProperty,isGarageInsurance);
        this.contracts.put(newId,houseFlatInsurance);
        this.idCounter.incrementAndGet();
        insurer.addContractForUser(houseFlatInsurance.getIdContract());
        return houseFlatInsurance;
    }

    public Contract registerHouseHoldInsurance(User insurer, LocalDateTime startOfInsurance, LocalDateTime endOfInsurance, double amountOfInsuranceIndemnity, double monthlyPayment, PropertyType propertyType, Address address, double valueOfProperty, double valueOfHouseholdAppliances)
    {
        long newId = idCounter.get();
        HouseholdInsurance householdInsurance = new HouseholdInsurance(newId,insurer.getIdUser(),startOfInsurance,endOfInsurance,amountOfInsuranceIndemnity,monthlyPayment,propertyType,address,valueOfProperty,valueOfHouseholdAppliances);
        this.contracts.put(newId,householdInsurance);
        this.idCounter.incrementAndGet();
        insurer.addContractForUser(householdInsurance.getIdContract());
        return householdInsurance;
    }

    public boolean updateContract(Contract contract, User oldInsurer, User newInsurer)
    {
        if(oldInsurer.getIdUser() == newInsurer.getIdUser())
        {
            Contract oldContract = this.contracts.replace(contract.getIdContract(),contract);
            return oldContract != null;
        }

        boolean correct = oldInsurer.removeContractOfUser(contract.getIdContract());
        if(!correct)
            return false;

        Contract oldContract = this.contracts.replace(contract.getIdContract(),contract);
        if(oldContract == null)
            return false;
        newInsurer.addContractForUser(contract.getIdContract());

        return true;
    }

    public Optional<Contract> findContractById(long idContract)
    {
        Contract findContract = this.contracts.get(idContract);
        return Optional.ofNullable(findContract);
    }

    public Map<Long,Contract> getAllContract()
    {
        return this.contracts;
    }

    public Set<Contract> getAllContractOfInsurer(Set<Long> contractsOfInsurer)
    {
        Set<Contract> contracts = new LinkedHashSet<>();
        for(Long id : contractsOfInsurer)
        {
            Optional<Contract> contractOptional = findContractById(id);
            contractOptional.ifPresent(contracts::add);
        }
        return contracts;
    }

}
