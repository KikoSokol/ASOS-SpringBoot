package com.example.springboot.backend.user.web.resource;


import com.example.springboot.backend.address.Address;
import com.example.springboot.backend.address.attribute.PostalCode;
import com.example.springboot.backend.address.attribute.PostalCodeParseException;
import com.example.springboot.backend.user.domain.User;
import com.example.springboot.backend.user.domain.attribute.PersonalNumber;
import com.example.springboot.backend.user.domain.attribute.PersonalNumberParseException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Set;
@Data
@NoArgsConstructor
public class UserResource
{

    private long idUser;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    @Size(min = 10,max = 11)
    private String personalNumber;
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String permanentStreet;
    @Positive
    private int permanentNumberOfHouse;
    @NotBlank
    private String permanentCity;
    @NotBlank
    @Size(min = 5,max = 6)
    private String permanentPostalCode;

    private String corespondenceStreet;
    private Integer corespondenceNumberOfHouse;
    private String corespondenceCity;
    private String corespondencePostalCode;

    public UserResource(User user)
    {
        this.idUser = user.getIdUser();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.personalNumber = user.getPersonalNumber().getPersonalNumber();
        this.email = user.getEmail();

        this.permanentStreet = user.getAddressOfPermanentResidence().getNameOfStreet();
        this.permanentNumberOfHouse = user.getAddressOfPermanentResidence().getNumberOfHouse();
        this.permanentCity = user.getAddressOfPermanentResidence().getNameOfCity();
        this.permanentPostalCode = user.getAddressOfPermanentResidence().getPostalCode().getPostalCode();

        this.corespondenceStreet = user.getAddressOfCorespondence().getNameOfStreet();
        this.corespondenceNumberOfHouse = user.getAddressOfCorespondence().getNumberOfHouse();
        this.corespondenceCity = user.getAddressOfCorespondence().getNameOfCity();
        this.corespondencePostalCode = user.getAddressOfCorespondence().getPostalCode().getPostalCode();
    }


    public User toUser(long idUser,Set<Long> contracts) throws PersonalNumberParseException, PostalCodeParseException
    {
        User user = new User(idUser, this.name, this.surname,new PersonalNumber(this.personalNumber), this.email,getPermanentAddress(),getCorespondenceAddress());
        user.setContracts(contracts);
        return user;
    }

    public Address getPermanentAddress() throws PostalCodeParseException
    {
        return new Address(this.permanentCity, this.permanentStreet,new PostalCode(this.permanentPostalCode), this.permanentNumberOfHouse);
    }

    public Address getCorespondenceAddress() throws PostalCodeParseException
    {
        if (isCorespondenceAllEmpty())
            return getPermanentAddress();

        Address address = new Address(this.corespondenceCity,corespondenceStreet,new PostalCode(this.corespondencePostalCode),this.corespondenceNumberOfHouse);
        return address;

    }

    public boolean isCorespondenceCorrect()
    {
        if(isCorespondenceAllEmpty())
            return true;
        return isCorespondenceAllEntered();
    }

    private boolean isCorespondenceAllEmpty()
    {
        return (this.corespondenceCity == null || this.corespondenceCity.trim().isEmpty()) && (this.corespondenceNumberOfHouse == null) && (this.corespondencePostalCode == null || this.corespondencePostalCode.trim().isEmpty()) && (this.corespondenceStreet == null || this.corespondenceStreet.trim().isEmpty());
    }

    private boolean isCorespondenceAllEntered()
    {
        return (this.corespondenceCity != null && !this.corespondenceCity.trim().isEmpty()) && (this.corespondenceNumberOfHouse != null) && (this.corespondencePostalCode != null && !this.corespondencePostalCode.trim().isEmpty()) && (this.corespondenceStreet != null && !this.corespondenceStreet.trim().isEmpty());
    }



}
