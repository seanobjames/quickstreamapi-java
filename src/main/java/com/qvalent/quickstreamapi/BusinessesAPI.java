package com.qvalent.quickstreamapi;

import org.apache.commons.lang3.StringUtils;

import com.qvalent.quickstreamapi.exception.NotFoundException;
import com.qvalent.quickstreamapi.model.request.CardSurchargeRequest;
import com.qvalent.quickstreamapi.model.response.Business;
import com.qvalent.quickstreamapi.model.response.BusinessCardSchemes;
import com.qvalent.quickstreamapi.model.response.BusinessCardSurcharge;
import com.qvalent.quickstreamapi.model.response.Businesses;
import com.qvalent.quickstreamapi.model.response.Link;
import com.qvalent.quickstreamapi.model.response.ResponseWrapper;
import com.qvalent.quickstreamapi.model.response.Result;
import com.qvalent.quickstreamapi.util.Http;
import com.qvalent.quickstreamapi.util.Http.AccessType;

public class BusinessesAPI extends Resource
{
    public BusinessesAPI( final Configuration configuration, final Http http )
    {
        super( configuration, http );
    }

    public Result<Business> get( final String supplierBusinessCode )
    {
        if( StringUtils.isBlank( supplierBusinessCode ) )
        {
            throw new NotFoundException();
        }
        final ResponseWrapper response = http.get( AccessType.SECRET_KEY, "/businesses/" + supplierBusinessCode );
        return new Result<Business>( response, Business.class );
    }

    public Result<Businesses> list( final Link pageLink )
    {
        if( pageLink == null )
        {
            throw new NotFoundException();
        }
        final ResponseWrapper response = http.get( AccessType.SECRET_KEY, pageLink );
        return new Result<Businesses>( response, Businesses.class );
    }

    public Result<Businesses> list()
    {
        final ResponseWrapper response = http.get( AccessType.SECRET_KEY, "/businesses" );
        return new Result<Businesses>( response, Businesses.class );
    }

    public Result<BusinessCardSchemes> listAcceptedCards( final String supplierBusinessCode )
    {
        if( StringUtils.isBlank( supplierBusinessCode ) )
        {
            throw new NotFoundException();
        }
        final ResponseWrapper response = http.get( AccessType.PUBLISHABLE_KEY, "/businesses/" + supplierBusinessCode + "/accepted-cards" );
        return new Result<BusinessCardSchemes>( response, BusinessCardSchemes.class );
    }

    public Result<BusinessCardSurcharge> queryCardSurcharge( final String supplierBusinessCode, final CardSurchargeRequest request )
    {
        if( request == null )
        {
            throw new NotFoundException();
        }
        final ResponseWrapper response = http.post( AccessType.PUBLISHABLE_KEY, "/businesses/" + supplierBusinessCode + "/query-card-surcharge", request );
        return new Result<BusinessCardSurcharge>( response, BusinessCardSurcharge.class );
    }
}
