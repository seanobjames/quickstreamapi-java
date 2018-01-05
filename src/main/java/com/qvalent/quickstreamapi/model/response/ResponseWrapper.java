package com.qvalent.quickstreamapi.model.response;

public class ResponseWrapper
{
    protected Error error;
    protected String json;

    public ResponseWrapper( final String json, final int statusCode )
    {
        this.json = json;
        if( statusCode == 422 )
        {
            error = Error.from( this.json );
        }
        else
        {
            error = null;
        }
    }

    public boolean isValidationError()
    {
        return error != null;
    }

    public Error getError()
    {
        return error;
    }

    public String getJson()
    {
        return json;
    }
}
