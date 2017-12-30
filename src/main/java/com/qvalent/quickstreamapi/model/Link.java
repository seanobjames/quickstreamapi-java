package com.qvalent.quickstreamapi.model;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.qvalent.quickstreamapi.util.Http.RequestMethod;

public class Link
{
    final String myRel;
    final URL myHref;
    final RequestMethod myRequestMethod;

    public Link( final String rel, final URL href, final RequestMethod requestMethod )
    {
        myRel = rel;
        myHref = href;
        myRequestMethod = requestMethod;
    }

    public static Link from( final JSONObject json ) throws JSONException, MalformedURLException
    {
        return new Link(
                json.getString( "rel" ),
                new URL( json.getString( "href" ) ),
                RequestMethod.valueOf( json.getString( "requestMethod" ) ) );
    }

    @Override
    public String toString()
    {
        return "Link [rel=" + myRel + ", href=" + myHref + ", requestMethod=" + myRequestMethod + "]";
    }
}
