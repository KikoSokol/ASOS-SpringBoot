package com.example.springboot.backend.user.service;


import com.example.springboot.backend.address.Address;
import com.example.springboot.backend.user.domain.User;
import com.example.springboot.backend.user.domain.attribute.PersonalNumber;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService
{
    User registerUserWithCorespondenceAddress(String name, String surname, PersonalNumber personalNumber, String email, Address addressOfPermanentResidence, Address addressOfCorespondence);
    User registerUserWithoutCorespondenceAddress(String name, String surname, PersonalNumber personalNumber, String email, Address addressOfPermanentResidence);
    Optional<User> findUserById(long id);
}

