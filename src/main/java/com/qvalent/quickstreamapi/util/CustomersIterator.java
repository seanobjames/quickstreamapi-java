package com.qvalent.quickstreamapi.util;

import java.util.Iterator;
import java.util.List;

import com.qvalent.quickstreamapi.QuickstreamAPI;
import com.qvalent.quickstreamapi.model.response.Customer;
import com.qvalent.quickstreamapi.model.response.Customers;
import com.qvalent.quickstreamapi.model.response.Link;
import com.qvalent.quickstreamapi.model.response.Result;

public class CustomersIterator implements Iterator<Customer>
{
    private List<Customer> list;
    private Iterator<Customer> iterator;
    private Customers customers;
    private final QuickstreamAPI quickstreamAPI;

    public CustomersIterator( final Customers customers )
    {
        quickstreamAPI = new QuickstreamAPI();
        populate( customers );
    }

    private void populate( final Customers customers )
    {
        this.customers = customers;
        list = customers.getData();
        iterator = list.iterator();
    }

    private boolean nextPage()
    {
        final Link nextLink = customers.nextPage();
        if( nextLink != null )
        {
            final Result<Customers> result = quickstreamAPI.customers().list( nextLink );
            populate( result.getTarget() );
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean hasNext()
    {
        if( iterator.hasNext() )
        {
            return true;
        }
        else
        {
            return nextPage();
        }
    }

    @Override
    public Customer next()
    {
        return iterator.next();
    }

}
