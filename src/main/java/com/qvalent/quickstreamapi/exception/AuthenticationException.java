package com.qvalent.quickstreamapi.exception;

import com.qvalent.quickstreamapi.model.ErrorResponse;

public class AuthenticationException extends QuickStreamAPIException
{
    private static final long serialVersionUID = 1L;

    public AuthenticationException( final ErrorResponse error )
    {
        super( error );
    }
}
