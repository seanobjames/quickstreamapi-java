package com.qvalent.quickstreamapi.exception;

public class TimeoutException extends QuickStreamAPIException
{
    private static final long serialVersionUID = 1L;

    public TimeoutException( final String message, final Throwable cause )
    {
        super( message, cause );
    }
}
