package com.qvalent.quickstreamapi;

import com.qvalent.quickstreamapi.util.Http;

/**
* This is the primary interface to the Quickstream API. It is used
* to interact with:
* <ul>
* <li> {@link SingleUseTokensAPI SingleUseTokens}</li>
* </ul>
*
* Quick start example:
*
* <pre>
* import com.qvalent.quickstreamapi.*;
*
* public class QuickstreamAPIExample
* {
*     final QuickstreamAPI quickstreamAPI = new QuickstreamAPI(
*         Environment.TEST,
*         &quot;thePublishableKey&quot;,
*         &quot;theSecretKey&quot; );
*
*     final CardRequest cardRequest = new CardRequestBuilder( &quot;theSupplierCode&quot; )
*             .cardNumber( &quot;4242424242424242&quot; ).expiryDateMonth( &quot;01&quot; ).expiryDateYear( &quot;2050&quot; ).build()
*
*     final SingleUseTokenResponse token = quickstreamAPI.singleUseTokens().generate( cardRequest );
*
*     System.out.print( &quot;Single Use Token Id: &quot; + token.getSingleUseTokenId() );
* }
* </pre>
*/
public class QuickstreamAPI
{
    private final Configuration configuration;
    private final Http http;

    public QuickstreamAPI()
    {
        configuration = new Configuration( Environment.PRODUCTION, "QUICKSTREAMDEMO_PUB", "QUICKSTREAMDEMO_SEC" );
        http = new Http( configuration );
    }

    public QuickstreamAPI( final Environment environment,
                           final String publishableKey,
                           final String secretKey )
    {
        configuration = new Configuration( environment, publishableKey, secretKey );
        http = new Http( configuration );
    }

    public void setProxy( final String url, final Integer port )
    {
        configuration.setProxy( url, port );
    }

    public Configuration getConfiguration()
    {
        return configuration;
    }

    public SingleUseTokensAPI singleUseTokens()
    {
        return new SingleUseTokensAPI( configuration, http );
    }

    public RootAPI root()
    {
        return new RootAPI( configuration, http );
    }

    public BusinessesAPI businesses()
    {
        return new BusinessesAPI( configuration, http );
    }

    public ApiKeysAPI apiKeys()
    {
        return new ApiKeysAPI( configuration, http );
    }

    public CreditCardsAPI creditCards()
    {
        return new CreditCardsAPI( configuration, http );
    }

    public CustomersAPI customers()
    {
        return new CustomersAPI( configuration, http );
    }
}