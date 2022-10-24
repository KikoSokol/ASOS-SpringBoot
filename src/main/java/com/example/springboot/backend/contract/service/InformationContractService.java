package com.example.springboot.backend.contract.service;

import com.example.springboot.backend.contract.domain.Contract;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public interface InformationContractService
{
    Map<Long, Contract> getAllContract();
    Set<Contract> getAllContractOfInsurer(Set<Long> contractsOfInsurer);
}
