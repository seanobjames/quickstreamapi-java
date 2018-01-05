package com.qvalent.quickstreamapi.model.response;

import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qvalent.quickstreamapi.exception.UnexpectedException;
import com.qvalent.quickstreamapi.util.Http.RequestMethod;

public class ValidationErrors
{
    private final List<Link> links;
    private final List<ValidationError> errors;
    private String status;
    private RequestMethod requestMethod;
    private URL requestUrl;
    private String customerMessage;
    private String developerMessage;

    public ValidationErrors()
    {
        links = null;
        errors = null;
        status = null;
        requestMethod = null;
        requestUrl = null;
        customerMessage = null;
        developerMessage = null;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus( final String status )
    {
        this.status = status;
    }

    public RequestMethod getRequestMethod()
    {
        return requestMethod;
    }

    public void setRequestMethod( final RequestMethod requestMethod )
    {
        this.requestMethod = requestMethod;
    }

    public URL getRequestUrl()
    {
        return requestUrl;
    }

    public void setRequestUrl( final URL requestUrl )
    {
        this.requestUrl = requestUrl;
    }

    public String getCustomerMessage()
    {
        return customerMessage;
    }

    public void setCustomerMessage( final String customerMessage )
    {
        this.customerMessage = customerMessage;
    }

    public String getDeveloperMessage()
    {
        return developerMessage;
    }

    public void setDeveloperMessage( final String developerMessage )
    {
        this.developerMessage = developerMessage;
    }

    public List<Link> getLinks()
    {
        return links;
    }

    public List<ValidationError> getErrors()
    {
        return errors;
    }

    public static ValidationErrors from( final String json )
    {
        final ObjectMapper mapper = new ObjectMapper();
        try
        {
            return mapper.readValue( json, ValidationErrors.class );
        }
        catch ( final Exception e )
        {
            throw new UnexpectedException( "Parsing error response failed.", e );
        }
    }

    @Override
    public String toString()
    {
        return "ValidationErrors [links=" + links
                + ", errors=" + errors
                + ", status=" + status
                + ", requestMethod=" + requestMethod
                + ", requestUrl=" + requestUrl
                + ", customerMessage=" + customerMessage
                + ", developerMessage=" + developerMessage + "]";
    }

}
