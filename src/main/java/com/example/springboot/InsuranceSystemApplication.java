package com.example.springboot;


import com.example.springboot.backend.InsuranceSystemService;
import com.example.springboot.backend.address.Address;
import com.example.springboot.backend.address.attribute.PostalCode;
import com.example.springboot.backend.contract.domain.PropertyType;
import com.example.springboot.backend.contract.domain.PurposeOfTrip;
import com.example.springboot.backend.contract.domain.TerritorialValidity;
import com.example.springboot.backend.user.domain.User;
import com.example.springboot.backend.user.domain.attribute.PersonalNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@Slf4j
@SpringBootApplication
public class InsuranceSystemApplication implements CommandLineRunner
{
    private final InsuranceSystemService insuranceSystemService;

    public InsuranceSystemApplication(InsuranceSystemService insuranceSystemService)
    {
        this.insuranceSystemService = insuranceSystemService;
    }

    public static void main(String[] args) {
//        log.info("Open in browser: http://localhost:8081");
        SpringApplication.run(InsuranceSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        PostalCode postalCode = new PostalCode("00000");
        Address address = new Address("Mesto1","Ulica1",postalCode,5);
        PersonalNumber personalNumber = new PersonalNumber("000000/0011");
        User user = insuranceSystemService.getUserService().registerUserWithoutCorespondenceAddress("Kristian","Sokol",personalNumber,"kikosokol@gmail.com",address);

        PostalCode postalCode2 = new PostalCode("11111");
        Address address2 = new Address("Mesto2","Ulica2",postalCode2,6);
        PersonalNumber personalNumber2 = new PersonalNumber("000000/0022");
        User user2 = insuranceSystemService.getUserService().registerUserWithoutCorespondenceAddress("Peter","Tester",personalNumber2,"peter@gmail.com",address2);


        insuranceSystemService.getContractService().registerTravelInsurance(user, LocalDateTime.of(2020,9,1,0,0),LocalDateTime.of(2020,9,10,23,59),10,2,user,true, PurposeOfTrip.SPORT);
        insuranceSystemService.getContractService().registerAccidentInsurance(user,LocalDateTime.of(2020,10,1,0,0),LocalDateTime.of(2020,10,20,23,59),10.0,15.0,user2,20.0,25.0,30.0, TerritorialValidity.SLOVAKIA_PLUS_WORLD);
        insuranceSystemService.getContractService().registerHouseFlatInsurance(user2,LocalDateTime.of(2020,11,1,0,0),LocalDateTime.of(2020,11,25,23,59),50.0,5.0, PropertyType.WOODEN_FAMILY_HOUSE,address,10000.0,true);
        insuranceSystemService.getContractService().registerHouseHoldInsurance(user2,LocalDateTime.of(2020,12,1,0,0),LocalDateTime.of(2020,12,30,23,59),25.0,10.0,PropertyType.BRICK_FAMILY_HOUSE,address2,3000.0,2000.0);
    }
}
