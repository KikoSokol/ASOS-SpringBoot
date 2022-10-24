package com.example.springboot.backend;


import com.example.springboot.backend.contract.service.ContractService;
import com.example.springboot.backend.contract.service.InformationContractService;
import com.example.springboot.backend.contract.service.UpdateContractService;
import com.example.springboot.backend.user.service.InformationUserService;
import com.example.springboot.backend.user.service.UpdateUserService;
import com.example.springboot.backend.user.service.UserService;

public interface InsuranceSystemService
{
    UserService getUserService();
    void setUserService(UserService userService);
    InformationUserService getInformationUserService();
    void setInformationUserService(InformationUserService informationUserService);
    UpdateUserService getUpdateUser();
    void setUpdateUser(UpdateUserService updateUser);
    ContractService getContractService();
    void setContractService(ContractService contractService);
    UpdateContractService getUpdateContractService();
    void setUpdateContractService(UpdateContractService updateContractService);
    InformationContractService getInformationContractService();
    void setInformationContractService(InformationContractService informationContractService);
}
