package com.qvalent.quickstreamapi.model.response;

import java.util.ArrayList;

public class SingleUseToken
{
    private final String singleUseTokenId;
    private final ArrayList<Link> links;

    public SingleUseToken()
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

    @Override
    public String toString()
    {
        return "SingleUseToken [singleUseTokenId=" + singleUseTokenId + "]";
    }
}
