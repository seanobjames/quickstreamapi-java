package com.qvalent.quickstreamapi;

import com.qvalent.quickstreamapi.model.response.ResponseWrapper;
import com.qvalent.quickstreamapi.model.response.Result;
import com.qvalent.quickstreamapi.model.response.Root;
import com.qvalent.quickstreamapi.util.Http;
import com.qvalent.quickstreamapi.util.Http.AccessType;

public class RootAPI extends Resource
{
    public RootAPI( final Configuration configuration, final Http http )
    {
        super( configuration, http );
    }

    public Result<Root> get()
    {
        final ResponseWrapper response = http.get( AccessType.PUBLISHABLE_KEY, "/" );
        return new Result<Root>( response, Root.class );
    }
}
