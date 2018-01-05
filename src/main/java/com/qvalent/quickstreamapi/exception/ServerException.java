package com.qvalent.quickstreamapi.exception;

import com.qvalent.quickstreamapi.model.response.ValidationErrors;

public class ServerException extends QuickStreamAPIException
{
    private static final long serialVersionUID = 1L;

    public ServerException( final ValidationErrors errors )
    {
        super( errors );
    }
}
