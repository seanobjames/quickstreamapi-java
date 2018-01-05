package com.qvalent.quickstreamapi.exception;

import com.qvalent.quickstreamapi.model.response.ValidationErrors;

public class TooManyRequestsException extends QuickStreamAPIException
{
    private static final long serialVersionUID = 1L;

    public TooManyRequestsException( final ValidationErrors errors )
    {
        super( errors );
    }
}
