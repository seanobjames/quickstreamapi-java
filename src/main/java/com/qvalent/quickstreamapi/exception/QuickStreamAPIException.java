package com.qvalent.quickstreamapi.exception;

import com.qvalent.quickstreamapi.model.response.Error;

public class QuickStreamAPIException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public QuickStreamAPIException( final String message, final Throwable cause )
    {
        super( message, cause );
    }

    public QuickStreamAPIException( final String message )
    {
        super( message );
    }

    public QuickStreamAPIException( final Error error )
    {
        super( error.getDeveloperMessage() );
    }

    public QuickStreamAPIException()
    {
        super();
    }
}
