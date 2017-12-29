package com.qvalent.quickstreamapi.exception;

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

    public QuickStreamAPIException()
    {
        super();
    }
}
