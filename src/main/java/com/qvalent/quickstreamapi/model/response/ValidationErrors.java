package com.qvalent.quickstreamapi.model.response;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qvalent.quickstreamapi.exception.UnexpectedException;
import com.qvalent.quickstreamapi.util.Http.RequestMethod;

public class ValidationErrors
{
    private final Links links;
    private final ArrayList<ValidationError> errors;
    private final String status;
    private final RequestMethod requestMethod;
    private final URL requestUrl;
    private final String customerMessage;
    private final String developerMessage;
    private final String traceCode;

    public ValidationErrors()
    {
        links = null;
        errors = null;
        status = null;
        requestMethod = null;
        requestUrl = null;
        customerMessage = null;
        developerMessage = null;
        traceCode = null;
    }

    public String getStatus()
    {
        return status;
    }

    public RequestMethod getRequestMethod()
    {
        return requestMethod;
    }

    public URL getRequestUrl()
    {
        return requestUrl;
    }

    public String getCustomerMessage()
    {
        return customerMessage;
    }

    public String getDeveloperMessage()
    {
        return developerMessage;
    }

    public Links getLinks()
    {
        return links;
    }

    public List<ValidationError> getErrors()
    {
        return errors;
    }

    public String getTraceCode()
    {
        return traceCode;
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
                + ", developerMessage=" + developerMessage
                + ", traceCode=" + traceCode+ "]";
    }

}
