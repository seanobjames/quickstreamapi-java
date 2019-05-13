package com.qvalent.quickstreamapi;

import org.apache.commons.lang3.StringUtils;

import com.qvalent.quickstreamapi.exception.NotFoundException;
import com.qvalent.quickstreamapi.model.response.BusinessCardScheme;
import com.qvalent.quickstreamapi.model.response.ResponseWrapper;
import com.qvalent.quickstreamapi.model.response.Result;
import com.qvalent.quickstreamapi.util.Http;
import com.qvalent.quickstreamapi.util.Http.AccessType;

public class CreditCardsAPI extends Resource
{
    public CreditCardsAPI( final Configuration configuration, final Http http )
    {
        super( configuration, http );
    }

    public Result<BusinessCardScheme> getCardScheme( final String cardBin )
    {
        if( StringUtils.length( cardBin )  != 6 )
        {
            throw new NotFoundException();
        }

        final ResponseWrapper response = http.get( AccessType.PUBLISHABLE_KEY, "/credit-cards/card-schemes/" + cardBin );
        return new Result<BusinessCardScheme>( response, BusinessCardScheme.class );
    }
}
