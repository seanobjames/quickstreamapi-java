package com.qvalent.quickstreamapi;

import org.json.JSONObject;

import com.qvalent.quickstreamapi.model.SingleUseToken;
import com.qvalent.quickstreamapi.util.Http;
import com.qvalent.quickstreamapi.util.Http.AccessType;

public class SingleUseTokensAPI extends Resource
{
    public SingleUseTokensAPI( final Configuration configuration, final Http http )
    {
        super( configuration, http );
    }

    public SingleUseToken generate()
    {
        final JSONObject json = myHttp.post( AccessType.PUBLISHABLE_KEY, "/single-use-tokens" );
        return SingleUseToken.from( json );
    }
}
