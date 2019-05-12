package com.qvalent.quickstreamapi.exception;

import com.qvalent.quickstreamapi.model.response.ValidationErrors;

public class InvalidRequestException extends QuickStreamAPIException
{
    private static final long serialVersionUID = 1L;

    public InvalidRequestException( final ValidationErrors errors )
    {
        super( errors );
    }

    public InvalidRequestException()
    {
        super();
    }
}
