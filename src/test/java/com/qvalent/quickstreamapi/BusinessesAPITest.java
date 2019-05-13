package com.qvalent.quickstreamapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.qvalent.quickstreamapi.model.request.CardSurchargeRequest;
import com.qvalent.quickstreamapi.model.request.CardSurchargeRequest.CardSurchargeRequestBuilder;
import com.qvalent.quickstreamapi.model.response.Business;
import com.qvalent.quickstreamapi.model.response.BusinessCardSchemes;
import com.qvalent.quickstreamapi.model.response.BusinessCardSurcharge;
import com.qvalent.quickstreamapi.model.response.Businesses;
import com.qvalent.quickstreamapi.model.response.CardScheme;
import com.qvalent.quickstreamapi.model.response.Link;
import com.qvalent.quickstreamapi.model.response.Result;

public class BusinessesAPITest
{
    private QuickstreamAPI quickstreamAPI;
    private CardSurchargeRequest cardSurchargeRequest;

    @Before
    public void before()
    {
        quickstreamAPI = new QuickstreamAPI(
            Environment.PRODUCTION,
            "QUICKSTREAMDEMO_PUB",
            "QUICKSTREAMDEMO_SEC"
        );

        cardSurchargeRequest = new CardSurchargeRequestBuilder()
                .cardNumber( "4242424242424242" )
                .build();
    }

    @Test
    public void getAcceptedCardsSuccess()
    {
        final Result<BusinessCardSchemes> result = quickstreamAPI.businesses().listAcceptedCards( "QUICKSTREAMDEMO" );
        assertNotNull( result.getTarget().getData() );
        assertEquals( 6,  result.getTarget().getData().size() );
    }

    @Test
    public void queryCardSurchargeSuccess()
    {
        final Result<BusinessCardSurcharge> result = quickstreamAPI.businesses().queryCardSurcharge( "QUICKSTREAMDEMO", cardSurchargeRequest );
        assertEquals( CardScheme.VISA, result.getTarget().getCardScheme() );
    }

    @Test
    public void getBusiness()
    {
        final Result<Business> result = quickstreamAPI.businesses().get( "QUICKSTREAMDEMO" );
        assertEquals( "QUICKSTREAMDEMO",  result.getTarget().getSupplierBusinessCode() );
    }

    @Test
    public void getBusinesses()
    {
        Result<Businesses> result = quickstreamAPI.businesses().list();
        Business business = null;
        while( business == null )
        {
            business = result.getTarget().getData()
                .stream()
                .filter( b -> b.getSupplierBusinessCode().equals( "QUICKSTREAMDEMO" ) )
                .findFirst()
                .orElse( null );

            final Link nextLink = result.getTarget().getLinks().getLink( "next" );
            if( business == null && nextLink != null )
            {
                result = quickstreamAPI.businesses().list( nextLink );
            }
        }
        assertNotNull( business );
        assertEquals( "QUICKSTREAMDEMO",  business.getSupplierBusinessCode() );
    }
}
