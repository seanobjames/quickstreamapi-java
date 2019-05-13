package com.qvalent.quickstreamapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.qvalent.quickstreamapi.model.response.Customer;
import com.qvalent.quickstreamapi.model.response.Customers;
import com.qvalent.quickstreamapi.model.response.Link;
import com.qvalent.quickstreamapi.model.response.Result;

public class CustomersAPITest
{
    private QuickstreamAPI quickstreamAPI;

    @Before
    public void before()
    {
        quickstreamAPI = new QuickstreamAPI(
            Environment.PRODUCTION,
            "QUICKSTREAMDEMO_PUB",
            "QUICKSTREAMDEMO_SEC"
        );
    }

    @Test
    public void getCustomer()
    {
        final Result<Customer> result = quickstreamAPI.customers().get( "214841327" );
        assertEquals( "JAMES1",  result.getTarget().getCustomerNumber() );
    }

    @Test
    public void searchCustomerByCustomerNumber()
    {
        final Result<Customers> result = quickstreamAPI.customers().search( "JAMES1", null, null );
        final Customer customer = result.getTarget().getData().get(0);
        assertEquals( "JAMES1",  customer.getCustomerNumber() );
    }

    @Test
    public void searchCustomerByEmailAddress()
    {
        final Result<Customers> result = quickstreamAPI.customers().search( null, null, "do-not-reply@qvalent.com" );
        final Customer customer = result.getTarget().getData().get(0);
        assertEquals( "JAMES1",  customer.getCustomerNumber() );
    }

    @Test
    public void searchCustomerByName()
    {
        final Result<Customers> result = quickstreamAPI.customers().search( null, "James Smith", null );
        final Customer customer = result.getTarget().getData().get(0);
        assertEquals( "JAMES1",  customer.getCustomerNumber() );
    }

    @Test
    public void searchCustomerByAllValues()
    {
        final Result<Customers> result = quickstreamAPI.customers().search( "JAMES1", "James Smith", "do-not-reply@qvalent.com" );
        final Customer customer = result.getTarget().getData().get(0);
        assertEquals( "JAMES1",  customer.getCustomerNumber() );
    }

    @Test
    public void listCustomers()
    {
        Result<Customers> result = quickstreamAPI.customers().list();
        Customer customer = null;
        while( customer == null )
        {
            customer = result.getTarget().getData()
                .stream()
                .filter( b -> b.getCustomerNumber().equals( "JAMES1" ) )
                .findFirst()
                .orElse( null );

            final Link nextLink = result.getTarget().getLinks().getLink( "next" );
            if( customer == null && nextLink != null )
            {
                result = quickstreamAPI.customers().list( nextLink );
            }
        }
        assertNotNull( customer );
        assertEquals( "JAMES1",  customer.getCustomerNumber() );
    }
}
