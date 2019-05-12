package com.qvalent.quickstreamapi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.qvalent.quickstreamapi.model.response.BusinessCardScheme;
import com.qvalent.quickstreamapi.model.response.CardScheme;
import com.qvalent.quickstreamapi.model.response.Result;

public class CreditCardsAPITest
{
    private QuickstreamAPI quickstreamAPI;

    @Before
    public void before()
    {
        quickstreamAPI = new QuickstreamAPI(
            Environment.PRODUCTION,
            "QUICKSTREAMDEMO_PUB",
            "QUICKSTREAMDEMO_SEC"
        );
    }

    @Test
    public void getCardScheme()
    {
        final Result<BusinessCardScheme> result = quickstreamAPI.creditCards().getCardScheme( "424242" );
        assertEquals( CardScheme.VISA, result.getTarget().getCardScheme() );
    }
}
