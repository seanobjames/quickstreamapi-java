package com.qvalent.quickstreamapi.model.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Request
{
    public String toJSON()
    {
        final ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            return objectMapper.writeValueAsString( this );
        }
        catch ( final JsonProcessingException e )
        {
            throw new RuntimeException( e );
        }
    }
}
