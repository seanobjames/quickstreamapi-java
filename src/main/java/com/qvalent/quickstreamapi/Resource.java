package com.qvalent.quickstreamapi;

import com.qvalent.quickstreamapi.util.Http;

/**
 * Abstract class for resource builders
 */
public abstract class Resource
{
    protected Configuration myConfiguration;
    protected Http myHttp;

    public Resource( final Configuration configuration, final Http http )
    {
        myConfiguration = configuration;
        myHttp = http;
    }

    public String toJSON()
    {
        throw new UnsupportedOperationException();
    }
}
