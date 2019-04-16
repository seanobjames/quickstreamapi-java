package com.qvalent.quickstreamapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.qvalent.quickstreamapi.model.request.CardSurchargeRequest;
import com.qvalent.quickstreamapi.model.request.CardSurchargeRequest.CardSurchargeRequestBuilder;
import com.qvalent.quickstreamapi.model.response.BusinessCardSchemes;
import com.qvalent.quickstreamapi.model.response.BusinessCardSurcharge;
import com.qvalent.quickstreamapi.model.response.CardScheme;
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
}
