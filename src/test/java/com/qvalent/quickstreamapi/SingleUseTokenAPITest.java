package com.qvalent.quickstreamapi;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.qvalent.quickstreamapi.exception.AuthenticationException;
import com.qvalent.quickstreamapi.exception.NotFoundException;
import com.qvalent.quickstreamapi.model.request.BankAccountRequest.BankAccountRequestBuilder;
import com.qvalent.quickstreamapi.model.request.CardRequest.CardRequestBuilder;
import com.qvalent.quickstreamapi.model.request.SingleUseTokenRequest;
import com.qvalent.quickstreamapi.model.response.Result;
import com.qvalent.quickstreamapi.model.response.SingleUseToken;

public class SingleUseTokenAPITest
{
    private QuickstreamAPI quickstreamAPI;
    private QuickstreamAPI badCredentialsAPI;
    private SingleUseTokenRequest cardRequest;
    private SingleUseTokenRequest badCardRequest;
    private SingleUseTokenRequest bankAccountRequest;
    private SingleUseTokenRequest badBankAccountRequest;

    @Before
    public void before()
    {
        badCredentialsAPI = new QuickstreamAPI(
            Environment.TEST,
            "publishableKey",
            "secretKey"
        );

        quickstreamAPI = new QuickstreamAPI();

        cardRequest = new CardRequestBuilder( "QUICKSTREAMDEMO" )
            .cardholderName( null )
            .cardNumber( "4242424242424242" )
            .expiryDateMonth( "01" )
            .expiryDateYear( "2050" )
            .cvn( "123" )
            .build();

        badCardRequest = new CardRequestBuilder( "QUICKSTREAMDEMO" )
                .cardholderName( null )
                .cardNumber( "XXXXXXXXXXXXXXXX" )
                .expiryDateMonth( "01" )
                .expiryDateYear( "2050" )
                .cvn( "123" )
                .build();

        bankAccountRequest = new BankAccountRequestBuilder( "QUICKSTREAMDEMO" )
                .accountName( "Jane Smith" )
                .bsb( "032-002" )
                .accountNumber( "123465" )
                .build();

        badBankAccountRequest = new BankAccountRequestBuilder( "QUICKSTREAMDEMO" )
                .accountName( null )
                .bsb( "032-002" )
                .accountNumber( "123465" )
                .build();
    }

    @Test( expected=AuthenticationException.class )
    public void generateSingleUseTokenWithIncorrectCredentials()
    {
        badCredentialsAPI.singleUseTokens().generate( cardRequest );
    }

    @Test()
    public void generateCardSingleUseTokenWithBadRequest()
    {
        final Result<SingleUseToken> result = quickstreamAPI.singleUseTokens().generate( badCardRequest );
        assertNotNull( result.getErrors() );
        assertNull( result.getTarget() );
    }

    @Test()
    public void generateBankAccountSingleUseTokenWithBadRequest()
    {
        final Result<SingleUseToken> result = quickstreamAPI.singleUseTokens().generate( badBankAccountRequest );
        assertNotNull( result.getErrors() );
        assertNull( result.getTarget() );
    }

    @Test( expected=NotFoundException.class )
    public void generateSingleUseTokenWithNoCardRequest()
    {
        quickstreamAPI.singleUseTokens().generate( null );
    }

    @Test
    public void generateCardSingleUseTokenRequestSuccess()
    {
        final Result<SingleUseToken> result = quickstreamAPI.singleUseTokens().generate( cardRequest );
        assertNotNull( result.getTarget().getSingleUseTokenId()  );
    }

    @Test
    public void generateBankAccountSingleUseTokenRequestSuccess()
    {
        final Result<SingleUseToken> result = quickstreamAPI.singleUseTokens().generate( bankAccountRequest );
        assertNotNull( result.getTarget().getSingleUseTokenId()  );
    }
}
