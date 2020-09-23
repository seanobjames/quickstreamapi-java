package com.qvalent.quickstreamapi.model.response;

import java.util.Iterator;

import com.qvalent.quickstreamapi.util.BusinessesIterator;

public class Businesses extends PaginatedResponse<Business> implements Iterable<Business>
{
    public Businesses()
    {
        super();
    }

    @Override
    public Iterator<Business> iterator()
    {
        return new BusinessesIterator( this );
    }
}
