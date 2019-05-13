package com.qvalent.quickstreamapi;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.qvalent.quickstreamapi.exception.NotFoundException;
import com.qvalent.quickstreamapi.model.response.Customer;
import com.qvalent.quickstreamapi.model.response.Customers;
import com.qvalent.quickstreamapi.model.response.Link;
import com.qvalent.quickstreamapi.model.response.ResponseWrapper;
import com.qvalent.quickstreamapi.model.response.Result;
import com.qvalent.quickstreamapi.util.Http;
import com.qvalent.quickstreamapi.util.Http.AccessType;
import com.qvalent.quickstreamapi.util.StringUtil;

public class CustomersAPI extends Resource
{
    public CustomersAPI( final Configuration configuration, final Http http )
    {
        super( configuration, http );
    }

    public Result<Customer> get( final String customerId )
    {
        if( StringUtils.isEmpty( customerId ) )
        {
            throw new NotFoundException();
        }

        final ResponseWrapper response = http.get( AccessType.SECRET_KEY, "/customers/" + customerId );
        return new Result<Customer>( response, Customer.class );
    }

    public Result<Customers> list()
    {
        final ResponseWrapper response = http.get( AccessType.SECRET_KEY, "/customers" );
        return new Result<Customers>( response, Customers.class );
    }

    public Result<Customers> list( final Link link )
    {
        final ResponseWrapper response = http.get( AccessType.SECRET_KEY, link );
        return new Result<Customers>( response, Customers.class );
    }

    public Result<Customers> search( final String customerNumber,
                                     final String customerName,
                                     final String customerEmail )
    {
        if( StringUtils.isEmpty( customerNumber )
                && StringUtils.isEmpty( customerName )
                && StringUtils.isEmpty( customerEmail ) )
        {
            return list();
        }
        else
        {
            final Map<String,String> parameters = new HashMap<>();
            parameters.put( "customerNumber", customerNumber );
            parameters.put( "customerName", customerName );
            parameters.put( "emailAddress", customerEmail );

            final ResponseWrapper response = http.get( AccessType.SECRET_KEY, "/customers" + StringUtil.getQueryString( parameters ) );
            return new Result<Customers>( response, Customers.class );
        }
    }
}
