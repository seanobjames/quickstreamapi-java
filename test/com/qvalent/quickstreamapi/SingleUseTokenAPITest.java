package com.qvalent.quickstreamapi;

import org.junit.Test;

import com.qvalent.quickstreamapi.model.SingleUseToken;

import org.junit.Before;

import static org.junit.Assert.*;

public class SingleUseTokenAPITest
{
    private QuickStreamAPI quickstreamAPI;
    
    @Before
    public void createQuickStreamAPI() 
    {
        this.quickstreamAPI = new QuickStreamAPI(
            Environment.DEVELOPMENT,
            "publishableKey",
            "secretKey"
        );
    }
    
    @Test
    public void generateSingleUseTokenOnSuccess()
    {
        final SingleUseToken token = quickstreamAPI.singleUseTokens().generate();
        assertNotNull( token.getSingleUseToken() );
    }
}
