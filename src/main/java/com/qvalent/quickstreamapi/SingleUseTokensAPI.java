package com.qvalent.quickstreamapi;

import com.qvalent.quickstreamapi.model.request.CardRequest;
import com.qvalent.quickstreamapi.model.response.SingleUseTokenResponse;
import com.qvalent.quickstreamapi.util.Http;
import com.qvalent.quickstreamapi.util.Http.AccessType;

public class SingleUseTokensAPI extends Resource
{
    public SingleUseTokensAPI( final Configuration configuration, final Http http )
    {
        super( configuration, http );
    }

    public SingleUseTokenResponse generate( final CardRequest request )
    {
        final String json = http.post( AccessType.PUBLISHABLE_KEY, "/single-use-tokens", request );
        return SingleUseTokenResponse.from( json );
    }
}
