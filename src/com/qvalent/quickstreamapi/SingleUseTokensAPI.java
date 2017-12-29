package com.qvalent.quickstreamapi;

import org.json.JSONObject;

import com.qvalent.quickstreamapi.model.SingleUseToken;
import com.qvalent.quickstreamapi.util.Http;
import com.qvalent.quickstreamapi.util.Http.AccessType;

public class SingleUseTokensAPI
{
    private Configuration myConfiguration;
    private Http myHttp;
    
    public SingleUseTokensAPI( final Configuration configuration, final Http http )
    {
        myConfiguration = configuration;
        myHttp = http;
    }
    
    public SingleUseToken generate()
    {
        final JSONObject json = myHttp.get( AccessType.PUBLISHABLE_KEY, "/single-use-tokens" );
        return SingleUseToken.from( json );
    }
}
