package com.qvalent.quickstreamapi.model.response;

public class BusinessCardSurcharge extends BusinessCardScheme
{
    private final CardType cardType;
    private final String surchargePercentage;

    public BusinessCardSurcharge()
    {
        super();
        cardType = null;
        surchargePercentage = null;
    }

    public CardType getCardType()
    {
        return cardType;
    }

    public String getSurchargePercentage()
    {
        return surchargePercentage;
    }

    @Override
    public String toString()
    {
        return "BusinessCardSurcharge [cardType=" + cardType
                + ", surchargePercentage=" + surchargePercentage + "] "
                + super.toString();
    }
}
