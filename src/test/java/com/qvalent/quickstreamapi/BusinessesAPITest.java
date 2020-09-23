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
import com.qvalent.quickstreamapi.model.response.Result;

public class BusinessesAPITest
{
    private QuickstreamAPI quickstreamAPI;
    private CardSurchargeRequest cardSurchargeRequest;

    @Before
    public void before()
    {
        quickstreamAPI = new QuickstreamAPI();

        cardSurchargeRequest = new CardSurchargeRequestBuilder()
                .cardNumber( "4242424242424242" )
                .build();
    }

    @Test
    public void getAcceptedCardsSuccess()
    {
        final Result<BusinessCardSchemes> result = quickstreamAPI.businesses().listAcceptedCards( "QUICKSTREAMDEMO" );
        assertNotNull( result.getTarget().getData() );
        assertEquals( 5,  result.getTarget().getData().size() );
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
    public void getBusinesses_noInfiniteLoop()
    {
        final Result<Businesses> result = quickstreamAPI.businesses().list();
        Business business = null;
        for( final Business currentBusiness : result.getTarget() )
        {
            business = currentBusiness;
        }

        assertNotNull( business );
    }

    @Test
    public void getBusinesses()
    {
        final Result<Businesses> result = quickstreamAPI.businesses().list();
        Business business = null;
        for( final Business currentBusiness : result.getTarget() )
        {
            if( currentBusiness.getSupplierBusinessCode().equals( "QUICKSTREAMDEMO" ) )
            {
                business = currentBusiness;
                break;
            }
        }

        assertNotNull( business );
        assertEquals( "QUICKSTREAMDEMO",  business.getSupplierBusinessCode() );
    }
}
