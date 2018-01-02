package com.qvalent.quickstreamapi.exception;

import com.qvalent.quickstreamapi.model.response.Error;

public class AuthorizationException extends QuickStreamAPIException
{
    private static final long serialVersionUID = 1L;

    public AuthorizationException( final Error error )
    {
        super( error );
    }
}
