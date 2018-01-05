package com.qvalent.quickstreamapi.exception;

import com.qvalent.quickstreamapi.model.response.ValidationErrors;

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

    public QuickStreamAPIException( final ValidationErrors errors )
    {
        super( errors.getDeveloperMessage() );
    }

    public QuickStreamAPIException()
    {
        super();
    }
}
