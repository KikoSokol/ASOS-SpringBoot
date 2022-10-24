package com.example.springboot.backend.contract.service;


import com.example.springboot.backend.contract.domain.Contract;
import com.example.springboot.backend.user.domain.User;
import org.springframework.stereotype.Service;


@Service
public interface UpdateContractService
{
    boolean updateContract(Contract contract, User oldInsurer, User newInsurer);
}
