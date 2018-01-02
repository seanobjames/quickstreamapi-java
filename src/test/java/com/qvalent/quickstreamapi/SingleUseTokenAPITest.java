package com.qvalent.quickstreamapi;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.qvalent.quickstreamapi.exception.AuthenticationException;
import com.qvalent.quickstreamapi.model.request.CardRequest;
import com.qvalent.quickstreamapi.model.request.CardRequest.CardRequestBuilder;
import com.qvalent.quickstreamapi.model.response.SingleUseTokenResponse;

public class SingleUseTokenAPITest
{
    private QuickstreamAPI quickstreamAPI;
    private QuickstreamAPI badCredentialsAPI;
    private CardRequest cardRequest;

    @Before
    public void before()
    {
        badCredentialsAPI = new QuickstreamAPI(
            Environment.TEST,
            "publishableKey",
            "secretKey"
        );

        quickstreamAPI = new QuickstreamAPI(
            Environment.PRODUCTION,
            "QUICKSTREAMDEMO_PUB",
            "QUICKSTREAMDEMO_SEC"
        );

        cardRequest = new CardRequestBuilder( "QUICKSTREAMDEMO" )
            .cardholderName( null )
            .cardNumber( "4242424242424242" )
            .expiryDateMonth( "01" )
            .expiryDateYear( "2050" )
            .cvn( "123" )
            .build();
    }

    @Test( expected=AuthenticationException.class )
    public void generateSingleUseTokenWithIncorrectCredentials()
    {
        badCredentialsAPI.singleUseTokens().generate( cardRequest );
    }

    @Test
    public void generateSingleUseTokenSuccess()
    {

        final SingleUseTokenResponse token = quickstreamAPI.singleUseTokens().generate( cardRequest );
        assertNotNull( token.getSingleUseTokenId() );
    }
}
