package com.example.springboot.backend.user.service;


import com.example.springboot.backend.address.Address;
import com.example.springboot.backend.user.domain.User;
import com.example.springboot.backend.user.domain.attribute.PersonalNumber;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class IdUserService implements UserService, UpdateUserService,InformationUserService
{
    private Map<Long, User> users;
    private AtomicLong idCounter;

    public IdUserService()
    {
        this.users = new HashMap<>();
        this.idCounter = new AtomicLong(1);
    }

    public User registerUserWithCorespondenceAddress(String name, String surname, PersonalNumber personalNumber, String email, Address addressOfPermanentResidence, Address addressOfCorespondence)
    {
        long newId = idCounter.get();
        User newUser = new User(newId,name,surname,personalNumber,email,addressOfPermanentResidence,addressOfCorespondence);
        this.users.put(newId,newUser);
        idCounter.incrementAndGet();
        return newUser;
    }

    public User registerUserWithoutCorespondenceAddress(String name, String surname, PersonalNumber personalNumber, String email, Address addressOfPermanentResidence)
    {
        return registerUserWithCorespondenceAddress(name,surname,personalNumber,email,addressOfPermanentResidence,addressOfPermanentResidence);
    }

    public Optional<User> findUserById(long id)
    {
        User findUser = this.users.get(id);
        return Optional.ofNullable(findUser);
    }

    public boolean updateUser(User user)
    {
        User oldUser = this.users.replace(user.getIdUser(),user);
        return oldUser != null;
    }

    public Map<Long, User> getAllUsers()
    {
        return this.users;
    }

    public Set<Long> getAllContractsOfUserById(long idUser)
    {
        Optional<User> userOptional = findUserById(idUser);
        if(!userOptional.isPresent())
        {
            throw new IllegalArgumentException("User with " + idUser + "not exists");
        }
        User user = userOptional.get();
        return user.getContracts();
    }




}

