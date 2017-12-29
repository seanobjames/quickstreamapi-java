package com.qvalent.quickstreamapi;

import com.qvalent.quickstreamapi.util.Http;

/**
* This is the primary interface to the QuickStream API. It is used
* to interact with:
* <ul>
* <li> {@link SingleUseTokenAPI SingleUseTokens}</li>
* </ul>
* 
* Quick start example:
*
* <pre>
* import com.qvalent.quickstreamapi.*;
* 
* public class QuickStreamAPIExample
* {
*     QuickStreamAPI quickstreamAPI = new QuickStreamAPI( 
*         Environment.TEST, &quot;thePublishableKey&quot;, &quot;theSecretKey&quot; );
*     
*     SingleUseTokenRequest request = new SingleUseTokenRequest( &quot;theSupplierBusinessCode&quot; )
*         .cardAccount().number( &quot;4111111111111111&quot; ).expiryDate( &quot;2022-01&quot; ).done();
*     
*     SingleUseToken token = quickstreamAPI.singleUseTokenAPI().get(request);
*     System.out.print(&quot;Single Use Token: &quot; + token.getToken());
* }
* </pre>
*/
public class QuickStreamAPI
{
    private Configuration myConfiguration;
    private Http myHttp;
    
    public QuickStreamAPI( final Environment environment, 
                           final String publishableKey, 
                           final String secretKey )
    {
        myConfiguration = new Configuration( environment, publishableKey, secretKey );
        myHttp = new Http( myConfiguration );
    }
    
    public void setProxy( final String url, final Integer port )
    {
        myConfiguration.setProxy( url, port );
    }
    
    public Configuration getConfiguration() 
    {
        return myConfiguration;
    }
    
    public SingleUseTokensAPI singleUseTokens()
    {
        return new SingleUseTokensAPI( myConfiguration, myHttp );
    }
}