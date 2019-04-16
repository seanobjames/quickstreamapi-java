package com.qvalent.quickstreamapi.model.response;

public class Address
{
    private final String street1;
    private final String street2;
    private final String street3;
    private final String city;
    private final String state;
    private final String postcode;
    private final String country;

    public Address()
    {
        street1 = null;
        street2 = null;
        street3 = null;
        city = null;
        state = null;
        postcode = null;
        country = null;
    }

    public String getStreet1()
    {
        return street1;
    }

    public String getStreet2()
    {
        return street2;
    }

    public String getStreet3()
    {
        return street3;
    }

    public String getCity()
    {
        return city;
    }

    public String getState()
    {
        return state;
    }

    public String getPostcode()
    {
        return postcode;
    }

    public String getCountry()
    {
        return country;
    }

    @Override
    public String toString()
    {
        return "Address [street1=" + street1
                + ", street2=" + street2
                + ", street3=" + street3
                + ", city=" + city
                + ", state=" + state
                + ", postcode=" + postcode
                + ", country=" + country + "]";
    }
}
