package com.qvalent.quickstreamapi.model.response;

import java.util.Iterator;

import com.qvalent.quickstreamapi.util.CustomersIterator;

public class Customers extends PaginatedResponse<Customer> implements Iterable<Customer>
{
    public Customers()
    {
        super();
    }

    @Override
    public Iterator<Customer> iterator()
    {
        return new CustomersIterator( this );
    }
}
