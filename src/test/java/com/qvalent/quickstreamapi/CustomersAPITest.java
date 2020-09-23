package com.qvalent.quickstreamapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.qvalent.quickstreamapi.model.request.CustomerRequest;
import com.qvalent.quickstreamapi.model.request.CustomerRequest.CustomerRequestBuilder;
import com.qvalent.quickstreamapi.model.response.Customer;
import com.qvalent.quickstreamapi.model.response.Customers;
import com.qvalent.quickstreamapi.model.response.NotificationMedium;
import com.qvalent.quickstreamapi.model.response.Result;

public class CustomersAPITest
{
    private QuickstreamAPI quickstreamAPI;

    @Before
    public void before()
    {
        quickstreamAPI = new QuickstreamAPI();
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
        final Result<Customers> result = quickstreamAPI.customers().list();
        Customer customer = null;

        final Customers customers = result.getTarget();
        for( final Customer currentCustomer : customers )
        {
            if( currentCustomer.getCustomerNumber().equals( "JAMES1" ) )
            {
                customer = currentCustomer;
                break;
            }
        }

        assertNotNull( customer );
        assertEquals( "JAMES1",  customer.getCustomerNumber() );
    }

    @Test
    public void listCustomers_noInfiniteLoop()
    {
        final Result<Customers> result = quickstreamAPI.customers().list();
        Customer customer = null;

        final Customers customers = result.getTarget();
        for( final Customer currentCustomer : customers )
        {
            customer = currentCustomer;
        }

        assertNotNull( customer );
    }

    @Test
    public void updateCustomerDetails()
    {
        final Result<Customers> result = quickstreamAPI.customers().search( "JAMES1", null, null );
        Customer customer = result.getTarget().getData().get(0);

        final CustomerRequest request = new CustomerRequestBuilder( customer )
                .preferredNotificationMedium( NotificationMedium.EMAILANDSMS )
                .build();

        final Result<Customer> updateResult = quickstreamAPI.customers().update( customer.getCustomerId(), request );
        customer = updateResult.getTarget();
        assertEquals( NotificationMedium.EMAILANDSMS,  customer.getPreferredNotificationMedium() );

        final CustomerRequest revertRequest = new CustomerRequestBuilder( customer )
                .preferredNotificationMedium( NotificationMedium.EMAIL )
                .build();

        final Result<Customer> revertResult = quickstreamAPI.customers().update( customer.getCustomerId(), revertRequest );
        customer = revertResult.getTarget();
        assertEquals( NotificationMedium.EMAIL,  customer.getPreferredNotificationMedium() );
    }
}
