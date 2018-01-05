package com.qvalent.quickstreamapi.model.response;

public class ResponseWrapper
{
    protected ValidationErrors errors;
    protected String json;

    public ResponseWrapper( final String json, final int statusCode )
    {
        this.json = json;
        errors = null;
        if( statusCode == 422 )
        {
            errors = ValidationErrors.from( json );
        }
    }

    public boolean isValidationError()
    {
        return errors != null;
    }

    public ValidationErrors getErrors()
    {
        return errors;
    }

    public String getJson()
    {
        return json;
    }
}
