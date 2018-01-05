package com.qvalent.quickstreamapi.exception;

import com.qvalent.quickstreamapi.model.response.Error;

public class InvalidRequestException extends QuickStreamAPIException
{
    private static final long serialVersionUID = 1L;

    public InvalidRequestException( final Error error )
    {
        super( error );
    }
}
