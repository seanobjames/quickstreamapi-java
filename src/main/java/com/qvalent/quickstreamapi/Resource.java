package com.qvalent.quickstreamapi;

import com.qvalent.quickstreamapi.util.Http;

/**
 * Abstract class for resource builders
 */
public abstract class Resource
{
    protected final Configuration configuration;
    protected final Http http;

    public Resource( final Configuration configuration, final Http http )
    {
        this.configuration = configuration;
        this.http = http;
    }
}
