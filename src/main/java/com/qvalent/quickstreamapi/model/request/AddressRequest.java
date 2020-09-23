package com.qvalent.quickstreamapi.model.request;

import com.qvalent.quickstreamapi.model.response.Address;

public class AddressRequest extends Request
{
    private final String street1;
    private final String street2;
    private final String street3;
    private final String city;
    private final String state;
    private final String postcode;
    private final String country;

    public AddressRequest( final Address address )
    {
        street1 = address.getStreet1();
        street2 = address.getStreet2();
        street3 = address.getStreet3();
        city = address.getCity();
        state = address.getState();
        postcode = address.getPostcode();
        country = address.getCountry();
    }

    public AddressRequest( final AddressRequestBuilder builder )
    {
        street1 = builder.street1;
        street2 = builder.street2;
        street3 = builder.street3;
        city = builder.city;
        state = builder.state;
        postcode = builder.postcode;
        country = builder.country;
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

    public static class AddressRequestBuilder
    {
        private String street1;
        private String street2;
        private String street3;
        private String city;
        private String state;
        private String postcode;
        private String country;

        public AddressRequestBuilder()
        {
        }

        public AddressRequestBuilder street1( final String street1 )
        {
            this.street1 = street1;
            return this;
        }

        public AddressRequestBuilder street2( final String street2 )
        {
            this.street2 = street2;
            return this;
        }

        public AddressRequestBuilder street3( final String street3 )
        {
            this.street3 = street3;
            return this;
        }

        public AddressRequestBuilder city( final String city )
        {
            this.city = city;
            return this;
        }

        public AddressRequestBuilder state( final String state )
        {
            this.state = state;
            return this;
        }

        public AddressRequestBuilder postcode( final String postcode )
        {
            this.postcode = postcode;
            return this;
        }

        public AddressRequestBuilder country( final String country )
        {
            this.country = country;
            return this;
        }

        public AddressRequest build()
        {
            return new AddressRequest( this );
        }
    }
}
