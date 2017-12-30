package com.qvalent.quickstreamapi;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.qvalent.quickstreamapi.exception.AuthenticationException;
import com.qvalent.quickstreamapi.exception.AuthorizationException;
import com.qvalent.quickstreamapi.model.SingleUseToken;

public class SingleUseTokenAPITest
{
    private QuickStreamAPI quickstreamAPI;
    private QuickStreamAPI badCredentialsAPI;

    @Before
    public void createQuickStreamAPI()
    {
        badCredentialsAPI = new QuickStreamAPI(
            Environment.TEST,
            "publishableKey",
            "secretKey"
        );

        quickstreamAPI = new QuickStreamAPI(
            Environment.TEST,
            "QUICKSTREAMDEMO_PUB",
            "QUICKSTREAMDEMO_SEC"
        );
    }

    @Test( expected=AuthenticationException.class )
    public void generateSingleUseTokenWithIncorrectCredentials()
    {
        badCredentialsAPI.singleUseTokens().generate();
    }

    @Test( expected=AuthorizationException.class )
    public void generateSingleUseTokenSuccess()
    {
        final SingleUseToken token = quickstreamAPI.singleUseTokens().generate();
        assertNotNull( token.getSingleUseToken() );
    }
}
