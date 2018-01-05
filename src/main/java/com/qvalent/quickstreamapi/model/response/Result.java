package com.qvalent.quickstreamapi.model.response;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Result<T>
{
    private Error errors;
    private String json;
    private T target;

    public Result()
    {
    }

    private static <T> T newInstanceFromResponse( final ResponseWrapper response, final Class<T> modelClass )
    {
        try
        {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue( response.getJson(), modelClass );
        }
        catch( final Exception e )
        {
            throw new IllegalArgumentException( "Could not write response to modelClass: " + modelClass, e );
        }
    }

    public Result( final ResponseWrapper response, final Class<T> modelClass )
    {
        if( !response.isValidationError() )
        {
            this.target = newInstanceFromResponse( response, modelClass );
        }
        else
        {
            this.errors = response.getError();
        }
    }

    public Error getErrors()
    {
        return errors;
    }

    public String getJson()
    {
        return json;
    }

    public T getTarget()
    {
        return target;
    }
}
