package com.qvalent.quickstreamapi.exception;

import com.qvalent.quickstreamapi.model.response.Error;

public class ServerException extends QuickStreamAPIException
{
    private static final long serialVersionUID = 1L;

    public ServerException( final Error error )
    {
        super( error );
    }
}
