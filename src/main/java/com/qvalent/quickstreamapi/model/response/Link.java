package com.qvalent.quickstreamapi.model.response;

import java.net.URL;

import com.qvalent.quickstreamapi.util.Http.RequestMethod;

public class Link
{
    private final String rel;
    private final URL href;
    private final RequestMethod requestMethod;

    public Link()
    {
        rel = null;
        href = null;
        requestMethod = null;
    }

    public String getRel()
    {
        return rel;
    }

    public URL getHref()
    {
        return href;
    }

    public RequestMethod getRequestMethod()
    {
        return requestMethod;
    }

    @Override
    public String toString()
    {
        return "Link [rel=" + rel + ", href=" + href + ", requestMethod=" + requestMethod + "]";
    }
}
