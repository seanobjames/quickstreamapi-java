package com.qvalent.quickstreamapi.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.qvalent.quickstreamapi.exception.UnexpectedException;
import com.qvalent.quickstreamapi.util.Http.RequestMethod;

public class ErrorResponse
{
    private List<Link> myLinks = new ArrayList<>();
    private String myStatus = null;
    private RequestMethod myRequestMethod = null;
    private URL myRequestURL = null;
    private String myCustomerMessage = null;
    private String myDeveloperMessage = null;

    public ErrorResponse( final String status,
                      final RequestMethod requestMethod,
                      final URL requestURL,
                      final String customerMessage,
                      final String developerMessage )
    {
        myLinks = new ArrayList<>();
        myStatus = status;
        myRequestMethod = requestMethod;
        myRequestURL = requestURL;
        myCustomerMessage = customerMessage;
        myDeveloperMessage = developerMessage;
    }

    public static ErrorResponse from( final JSONObject json )
    {
        ErrorResponse error = null;
        try
        {
            error = new ErrorResponse(
                json.getString( "status" ),
                RequestMethod.valueOf( json.getString( "requestMethod" ) ),
                new URL( json.getString( "requestUrl" ) ),
                json.getString( "customerMessage" ),
                json.getString( "developerMessage" ) );

            for( final Object link : json.getJSONArray( "links" ) )
            {
                error.getLinks().add( Link.from( (JSONObject)link ) );
            }
        }
        catch ( JSONException | MalformedURLException e )
        {
            throw new UnexpectedException( e.getMessage() );
        }

        return error;
    }

    public String getStatus()
    {
        return myStatus;
    }

    public void setStatus( final String status )
    {
        myStatus = status;
    }

    public RequestMethod getRequestMethod()
    {
        return myRequestMethod;
    }

    public void setRequestMethod( final RequestMethod requestMethod )
    {
        myRequestMethod = requestMethod;
    }

    public URL getRequestURL()
    {
        return myRequestURL;
    }

    public void setRequestURL( final URL requestURL )
    {
        myRequestURL = requestURL;
    }

    public String getCustomerMessage()
    {
        return myCustomerMessage;
    }

    public void setCustomerMessage( final String customerMessage )
    {
        myCustomerMessage = customerMessage;
    }

    public String getDeveloperMessage()
    {
        return myDeveloperMessage;
    }

    public void setDeveloperMessage( final String developerMessage )
    {
        myDeveloperMessage = developerMessage;
    }

    public List<Link> getLinks()
    {
        return myLinks;
    }

    @Override
    public String toString()
    {
        return "Error [links=" + myLinks
                + ", status=" + myStatus
                + ", requestMethod=" + myRequestMethod
                + ", requestURL=" + myRequestURL
                + ", customerMessage=" + myCustomerMessage
                + ", developerMessage=" + myDeveloperMessage + "]";
    }
}
