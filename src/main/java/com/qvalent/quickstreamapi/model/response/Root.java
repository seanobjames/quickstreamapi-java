package com.qvalent.quickstreamapi.model.response;

public class Root
{
    private final Links links;
    private final String communityCode;
    private final String communityName;
    private final String keyName;

    public Root()
    {
        links = null;
        communityCode = null;
        communityName = null;
        keyName = null;
    }

    public Links getLinks()
    {
        return links;
    }

    public String getCommunityCode()
    {
        return communityCode;
    }

    public String getCommunityName()
    {
        return communityName;
    }

    public String getKeyName()
    {
        return keyName;
    }

    @Override
    public String toString()
    {
        return "RootResponse [links=" + links
                + ", communityCode=" + communityCode
                + ", communityName=" + communityName
                + ", keyName=" + keyName + "]";
    }
}
