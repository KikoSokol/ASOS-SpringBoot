package com.example.springboot.backend.user.domain;


import com.example.springboot.backend.address.Address;
import com.example.springboot.backend.user.domain.attribute.PersonalNumber;
import lombok.Data;

import javax.validation.constraints.Email;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class User
{
    private long idUser;
    private String name;
    private String surname;
    private PersonalNumber personalNumber;
    @Email
    private String email;
    private Address addressOfPermanentResidence;
    private Address addressOfCorespondence;
    private Set<Long> contracts;

    public User(long idUser, String name, String surname, PersonalNumber personalNumber, String email, Address addressOfPermanentResidence, Address addressOfCorespondence) {
        setIdUser(idUser);
        setName(name);
        setSurname(surname);
        setPersonalNumber(personalNumber);
        setEmail(email);
        setAddressOfPermanentResidence(addressOfPermanentResidence);
        setAddressOfCorespondence(addressOfCorespondence);

        contracts = new LinkedHashSet<>();
    }

    public User(long idUser, String name, String surname, PersonalNumber personalNumber, String email, Address addressOfPermanentResidence) {
        this(idUser,name,surname,personalNumber,email,addressOfPermanentResidence,addressOfPermanentResidence);
    }

    public long getIdUser() {
        return idUser;
    }

    private void setIdUser(long idUser)
    {
        if(idUser < 1)
        {
            throw new IllegalArgumentException("Bad id. Id must be greater than 0");
        }
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        if(name ==  null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException("Name is required");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname)
    {
        if(surname == null || surname.trim().isEmpty())
        {
            throw new IllegalArgumentException("Surname is required");
        }
        this.surname = surname;
    }

    public PersonalNumber getPersonalNumber() {
        return personalNumber;
    }

    private void setPersonalNumber(PersonalNumber personalNumber)
    {
        if(personalNumber == null)
        {
            throw new IllegalArgumentException("Personal number is required");
        }
        this.personalNumber = personalNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        if(email == null || email.trim().isEmpty())
        {
            throw new IllegalArgumentException("Email is required");
        }
        this.email = email;
    }

    public Address getAddressOfPermanentResidence() {
        return addressOfPermanentResidence;
    }

    public void setAddressOfPermanentResidence(Address addressOfPermanentResidence)
    {
        if(addressOfPermanentResidence == null)
        {
            throw new IllegalArgumentException("Address of permanent residence is required");
        }
        this.addressOfPermanentResidence = addressOfPermanentResidence;
    }

    public Address getAddressOfCorespondence() {
        return addressOfCorespondence;
    }

    public void setAddressOfCorespondence(Address addressOfCorespondence)
    {
        if(addressOfCorespondence == null)
        {
            throw new IllegalArgumentException("Address of corespondence is required");
        }
        this.addressOfCorespondence = addressOfCorespondence;
    }

    public Set<Long> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Long> contracts) {
        this.contracts = contracts;
    }

    public void addContractForUser(Long contract)
    {
        if (contract == null)
        {
            throw new IllegalArgumentException("Contract for add is required");
        }
        this.contracts.add(contract);
    }

    public String getUserInfo()
    {
        return this.idUser + ", " + this.name + " " + this.surname;
    }

    public boolean removeContractOfUser(Long contract)
    {
        return this.contracts.remove(contract);
    }

    @Override
    public String toString() {
        return  "ID USER: " + idUser +
                "  NAME:  " + name +
                "  SURNAME:  " + surname +
                "  PERSONAL_NUMBER:  " + personalNumber.getPersonalNumberWithSlash() +
                "  EMAIL:  " + email + "\n  " +
                "  ADDRESS_OF_PERMANENT_RESIDENCE:  " + addressOfPermanentResidence.toString() + "\n    " +
                "  ADDRESS_OF_CORESPONDENCE:  " + addressOfCorespondence.toString();
    }
}
