package com.qvalent.quickstreamapi.exception;

import com.qvalent.quickstreamapi.model.response.Error;

public class AuthenticationException extends QuickStreamAPIException
{
    private static final long serialVersionUID = 1L;

    public AuthenticationException( final Error error )
    {
        super( error );
    }
}
