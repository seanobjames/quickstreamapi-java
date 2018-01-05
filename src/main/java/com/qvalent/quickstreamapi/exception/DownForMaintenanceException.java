package com.qvalent.quickstreamapi.exception;

import com.qvalent.quickstreamapi.model.response.Error;

public class DownForMaintenanceException extends QuickStreamAPIException
{
    private static final long serialVersionUID = 1L;

    public DownForMaintenanceException( final Error error )
    {
        super( error );
    }
}
