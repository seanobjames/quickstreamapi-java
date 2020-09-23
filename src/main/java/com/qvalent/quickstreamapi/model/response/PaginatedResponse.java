package com.qvalent.quickstreamapi.model.response;

import java.util.ArrayList;

public class PaginatedResponse<T>
{
    private final Links links;
    private final ArrayList<T> data;

    public PaginatedResponse()
    {
        links = null;
        data = null;
    }

    public Links getLinks()
    {
        return links;
    }

    public ArrayList<T> getData()
    {
        return data;
    }

    private Link getLink( final String target )
    {
        Link nextLink = null;
        if( links != null )
        {
            nextLink = links.getLink( target );
        }
        return nextLink;
    }

    public Link nextPage()
    {
        return getLink( "next" );
    }

    public Link previousPage()
    {
        return getLink( "prev" );
    }

    @Override
    public String toString()
    {
        return "PaginatedResponse [links=" + links
                + ", data=" + data + "]";
    }
}

