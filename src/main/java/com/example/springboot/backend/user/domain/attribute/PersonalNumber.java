package com.example.springboot.backend.user.domain.attribute;


public class PersonalNumber
{
    private String personalNumber;

    public PersonalNumber(String personalNumber) throws PersonalNumberParseException
    {
        setPersonalNumber(personalNumber);
    }

    public String getPersonalNumber()
    {
        return this.personalNumber;
    }

    public void setPersonalNumber(String personalNumber) throws PersonalNumberParseException
    {

        if(personalNumber == null || personalNumber.trim().isEmpty())
        {
            throw new IllegalArgumentException("Personal number is required");
        }
        personalNumber = personalNumber.trim();

        boolean withSlash = isCorrectPersonalNumberWithSlash(personalNumber);
        if (withSlash)
        {
            this.personalNumber = personalNumber;
        }
        else
            this.personalNumber = addSlash(personalNumber);
    }

    private boolean isCorrectPersonalNumberWithSlash(String personalNumber) throws PersonalNumberParseException
    {
        int length = personalNumber.length();

        if(length < 10 || length > 11)
            throw new PersonalNumberParseException("Personal number has too many number");

        if(haveSlash(personalNumber))
        {
            withSlash(personalNumber);
            checkCorrect(deleteSlash(personalNumber));
            return true;
        }

        withoutSlash(personalNumber);
        checkCorrect(deleteSlash(personalNumber));
        return false;
    }

    private void withoutSlash(String personalNumber) throws PersonalNumberParseException
    {
        isStringOfNumber(personalNumber);
    }

    private void withSlash(String personalNumber) throws PersonalNumberParseException
    {
        String[] subNumber = personalNumber.split("/");
        if(subNumber.length > 2)
            throw new PersonalNumberParseException("Bad format of personal number");
        else if(subNumber[0].length() != 6 || subNumber[1].length() != 4)
            throw new PersonalNumberParseException("Bad format of personal user");

        isStringOfNumber(subNumber[0].concat(subNumber[1]));


    }

    private void isStringOfNumber(String personalNumber) throws PersonalNumberParseException
    {
        for(int i = 0; i < personalNumber.length();i++)
        {
            try
            {
                int check = Integer.parseInt("" + personalNumber.charAt(i));
            }
            catch (NumberFormatException e)
            {
                throw new PersonalNumberParseException("Personal number cannot contain a letter. Personal number must contain only number.");
            }
        }
    }

    private boolean haveSlash(String personalNumber)
    {
        return personalNumber.indexOf('/') != -1;
    }

    private String addSlash(String personalNumber)
    {
        if(haveSlash(personalNumber))
            return personalNumber;

        String newPersonalNumber = personalNumber.substring(0,6).concat("/");
        newPersonalNumber = newPersonalNumber.concat(personalNumber.substring(7));

        return newPersonalNumber;
    }

    private String deleteSlash(String personalNumber)
    {
        return personalNumber.substring(0,6).concat(personalNumber.substring(7));
    }

    private void checkCorrect(String personalNumber) throws PersonalNumberParseException
    {
        long check = Long.parseLong(personalNumber);
        if(check % 11 != 0)
            throw new PersonalNumberParseException("Personal number is not divisible by 11.");
    }

    public String getPersonalNumberWithSlash()
    {
        return addSlash(this.personalNumber);
    }

    public String getPersonalNumberWithoutSlash()
    {
        return deleteSlash(this.personalNumber);
    }


}

