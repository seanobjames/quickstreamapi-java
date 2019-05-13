package com.qvalent.quickstreamapi.model.response;

public class ApiKey
{
    private final Links links;
    private final String keyName;
    private final String key;
    private final String expiryDate;

    public ApiKey()
    {
        links = null;
        keyName = null;
        key = null;
        expiryDate = null;
    }

    public Links getLinks()
    {
        return links;
    }

    public String getKeyName()
    {
        return keyName;
    }

    public String getKey()
    {
        return key;
    }

    public String getExpiryDate()
    {
        return expiryDate;
    }

    @Override
    public String toString()
    {
        return "ApiKey [links=" + links
                + ", keyName=" + keyName
                + ", key=" + key
                + ", expiryDate=" + expiryDate + "]";
    }
}
