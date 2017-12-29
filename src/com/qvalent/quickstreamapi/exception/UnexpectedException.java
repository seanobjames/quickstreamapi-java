package com.qvalent.quickstreamapi.exception;

public class UnexpectedException extends QuickStreamAPIException
{
    private static final long serialVersionUID = 1L;
    
    public UnexpectedException( final String message )
    {
        super( message );
    }
    
    public UnexpectedException( final String message, final Throwable cause )
    {
        super( message, cause );
    }
}
