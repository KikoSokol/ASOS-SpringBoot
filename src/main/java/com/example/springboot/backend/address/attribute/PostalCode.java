package com.example.springboot.backend.address.attribute;

public class PostalCode
{
    private String postalCode;

    public PostalCode(String postalCode) throws PostalCodeParseException
    {
        setPostalCode(postalCode);
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) throws PostalCodeParseException
    {
        if(postalCode == null || postalCode.trim().isEmpty())
        {
            throw new IllegalArgumentException("Postal code is required");
        }
        postalCode = postalCode.trim();
        isCorrectPostalCode(postalCode);
        this.postalCode = addSpace(postalCode);
    }

    private void isCorrectPostalCode(String postalCode) throws PostalCodeParseException
    {
        if(postalCode.length() > 6 || postalCode.length() < 5)
        {
            throw new PostalCodeParseException("Postal code not exists");
        }

        if(postalCode.length() == 6)
        {
            String[] part = postalCode.split(" ");
            if (part.length != 2 && part[0].length() != 3)
            {
                throw new PostalCodeParseException("Wrong format of postal code");
            }
            String forCheck = part[0].concat(part[1]);
            isStringOfNumber(forCheck);
        }
        else
        {
            isStringOfNumber(postalCode);
        }




    }

    private void isStringOfNumber(String postalCode) throws PostalCodeParseException
    {
        for(int i = 0; i < postalCode.length();i++)
        {
            try
            {
                int check = Integer.parseInt("" + postalCode.charAt(i));
            }
            catch (NumberFormatException e)
            {
                throw new PostalCodeParseException("Postal code cannot contain a letter. Postal code must contain only number");
            }
        }
    }

    private String addSpace(String postalCode)
    {
        if(postalCode.length() == 5)
        {
            String newPostalCode = postalCode.substring(0,3).concat(" ");
            newPostalCode = newPostalCode.concat(postalCode.substring(3));
            return newPostalCode;
        }
        return postalCode;
    }

    public String getPostalCodeWithSpace()
    {
        return this.postalCode;
    }

    public String getPostalCodeWithoutSpace()
    {

        return this.postalCode.substring(0,3).concat(this.postalCode.substring(4));
    }
}
