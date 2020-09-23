package com.qvalent.quickstreamapi.util;

import java.util.Iterator;
import java.util.List;

import com.qvalent.quickstreamapi.QuickstreamAPI;
import com.qvalent.quickstreamapi.model.response.Business;
import com.qvalent.quickstreamapi.model.response.Businesses;
import com.qvalent.quickstreamapi.model.response.Link;
import com.qvalent.quickstreamapi.model.response.Result;

public class BusinessesIterator implements Iterator<Business>
{
    private List<Business> list;
    private Iterator<Business> iterator;
    private Businesses businesses;
    private final QuickstreamAPI quickstreamAPI;

    public BusinessesIterator( final Businesses businesses )
    {
        quickstreamAPI = new QuickstreamAPI();
        populate( businesses );
    }

    private void populate( final Businesses businesses )
    {
        this.businesses = businesses;
        list = businesses.getData();
        iterator = list.iterator();
    }

    private boolean nextPage()
    {
        final Link nextLink = businesses.nextPage();
        if( nextLink != null )
        {
            final Result<Businesses> result = quickstreamAPI.businesses().list( nextLink );
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
    public Business next()
    {
        return iterator.next();
    }

}
