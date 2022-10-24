package com.example.springboot.backend.contract.web;


import com.example.springboot.backend.InsuranceSystemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contract")
public class ContractController
{
    private final InsuranceSystemService insuranceSystemService;

    public ContractController(InsuranceSystemService insuranceSystemService) {
        this.insuranceSystemService = insuranceSystemService;
    }

    @GetMapping("/")
    public ResponseEntity all()
    {
        return new ResponseEntity<>(insuranceSystemService.getInformationContractService().getAllContract().values(), HttpStatus.OK);
    }

}
