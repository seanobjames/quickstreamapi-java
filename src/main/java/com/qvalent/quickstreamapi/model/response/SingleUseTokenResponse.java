package com.qvalent.quickstreamapi.model.response;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qvalent.quickstreamapi.exception.UnexpectedException;

public class SingleUseTokenResponse
{
    private final String singleUseTokenId;
    private final ArrayList<Link> links;

    public SingleUseTokenResponse()
    {
        singleUseTokenId = null;
        links = null;
    }

    public String getSingleUseTokenId()
    {
        return singleUseTokenId;
    }

    public ArrayList<Link> getLinks()
    {
        return links;
    }

    public static SingleUseTokenResponse from( final String json )
    {
        final ObjectMapper mapper = new ObjectMapper();
        try
        {
            return mapper.readValue( json, SingleUseTokenResponse.class );
        }
        catch ( final Exception e )
        {
            throw new UnexpectedException( "Parsing single-use-token response failed.", e );
        }
    }

    @Override
    public String toString()
    {
        return "SingleUseToken [singleUseTokenId=" + singleUseTokenId + "]";
    }
}
