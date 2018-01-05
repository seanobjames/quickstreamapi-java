package com.qvalent.quickstreamapi;

import com.qvalent.quickstreamapi.exception.NotFoundException;
import com.qvalent.quickstreamapi.model.request.CardRequest;
import com.qvalent.quickstreamapi.model.response.ResponseWrapper;
import com.qvalent.quickstreamapi.model.response.Result;
import com.qvalent.quickstreamapi.model.response.SingleUseToken;
import com.qvalent.quickstreamapi.util.Http;
import com.qvalent.quickstreamapi.util.Http.AccessType;

public class SingleUseTokensAPI extends Resource
{
    public SingleUseTokensAPI( final Configuration configuration, final Http http )
    {
        super( configuration, http );
    }

    public Result<SingleUseToken> generate( final CardRequest request )
    {
        if( request == null )
        {
            throw new NotFoundException();
        }
        final ResponseWrapper response = http.post( AccessType.PUBLISHABLE_KEY, "/single-use-tokens", request );
        return new Result<SingleUseToken>( response, SingleUseToken.class );
    }
}
