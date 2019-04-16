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

    public Link nextPage()
    {
        Link nextLink = null;
        if( links != null )
        {
            nextLink = links.getLink( "next" );
        }
        return nextLink;
    }

    public Link previousPage()
    {
        Link nextLink = null;
        if( links != null )
        {
            nextLink = links.getLink( "prev" );
        }
        return nextLink;
    }

    @Override
    public String toString()
    {
        return "PaginatedResponse [links=" + links
                + ", data=" + data + "]";
    }
}
