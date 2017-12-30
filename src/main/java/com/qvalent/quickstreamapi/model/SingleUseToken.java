package com.qvalent.quickstreamapi.model;

import org.json.JSONObject;

public class SingleUseToken
{
    private final String mySingleUseToken;

    public SingleUseToken( final String singleUseToken )
    {
        mySingleUseToken = singleUseToken;
    }

    public String getSingleUseToken()
    {
        return mySingleUseToken;
    }

    public static SingleUseToken from( final JSONObject json )
    {
        return new SingleUseToken( (String)json.get( "singleUseToken" ) );
    }

    @Override
    public String toString()
    {
        return "SingleUseToken [singleUseToken=" + mySingleUseToken + "]";
    }
}
