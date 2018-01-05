package com.qvalent.quickstreamapi.exception;

import com.qvalent.quickstreamapi.model.response.ValidationErrors;

public class AuthenticationException extends QuickStreamAPIException
{
    private static final long serialVersionUID = 1L;

    public AuthenticationException( final ValidationErrors errors )
    {
        super( errors );
    }
}
