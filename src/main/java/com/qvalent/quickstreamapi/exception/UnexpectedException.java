package com.qvalent.quickstreamapi.exception;

import com.qvalent.quickstreamapi.model.ErrorResponse;

public class UnexpectedException extends QuickStreamAPIException
{
    private static final long serialVersionUID = 1L;

    public UnexpectedException( final String message )
    {
        super( message );
    }

    public UnexpectedException( final String message, final ErrorResponse error )
    {
        super( error.toString() );
    }

    public UnexpectedException( final String message, final Throwable cause )
    {
        super( message, cause );
    }
}
