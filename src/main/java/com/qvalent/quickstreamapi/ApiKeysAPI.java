package com.qvalent.quickstreamapi;

import com.qvalent.quickstreamapi.model.response.ApiKey;
import com.qvalent.quickstreamapi.model.response.ResponseWrapper;
import com.qvalent.quickstreamapi.model.response.Result;
import com.qvalent.quickstreamapi.util.Http;
import com.qvalent.quickstreamapi.util.Http.AccessType;

public class ApiKeysAPI extends Resource
{
    public ApiKeysAPI( final Configuration configuration, final Http http )
    {
        super( configuration, http );
    }

    public Result<ApiKey> getLatest()
    {
        final ResponseWrapper response = http.get( AccessType.SECRET_KEY, "/api-keys/latest" );
        return new Result<ApiKey>( response, ApiKey.class );
    }
}
