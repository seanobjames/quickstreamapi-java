package com.qvalent.quickstreamapi.model.response;

import java.time.LocalDateTime;

public class ApiKey
{
    private final Links links;
    private final String keyName;
    private final String key;
    private final LocalDateTime expiryDate;

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

    public LocalDateTime getExpiryDate()
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
