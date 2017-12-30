package com.qvalent.quickstreamapi.exception;

import com.qvalent.quickstreamapi.model.ErrorResponse;

public class AuthorizationException extends QuickStreamAPIException
{
    private static final long serialVersionUID = 1L;

    public AuthorizationException( final ErrorResponse error )
    {
        super( error );
    }
}
