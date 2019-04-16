package com.qvalent.quickstreamapi.model.response;

public class CreditCard
{
    private final String cardNumber;
    private final String expiryDateMonth;
    private final String expiryDateYear;
    private final CardScheme cardScheme;
    private final CardType cardType;
    private final String cardholderName;
    private final String surchargePercentage;
    private final String maskedCardNumber4Digits;

    public CreditCard()
    {
        cardNumber = null;
        expiryDateMonth = null;
        expiryDateYear = null;
        cardScheme = null;
        cardType = null;
        cardholderName = null;
        surchargePercentage = null;
        maskedCardNumber4Digits = null;
    }

    public String getCardNumber()
    {
        return cardNumber;
    }

    public String getExpiryDateMonth()
    {
        return expiryDateMonth;
    }

    public String getExpiryDateYear()
    {
        return expiryDateYear;
    }

    public CardScheme getCardScheme()
    {
        return cardScheme;
    }

    public CardType getCardType()
    {
        return cardType;
    }

    public String getCardholderName()
    {
        return cardholderName;
    }

    public String getSurchargePercentage()
    {
        return surchargePercentage;
    }

    public String getMaskedCardNumber4Digits()
    {
        return maskedCardNumber4Digits;
    }

    @Override
    public String toString()
    {
        return "CreditCard [cardNumber=" + cardNumber
                + ", expiryDateMonth=" + expiryDateMonth
                + ", expiryDateYear=" + expiryDateYear
                + ", cardScheme=" + cardScheme
                + ", cardType=" + cardType + ", cardholderName=" + cardholderName
                + ", surchargePercentage=" + surchargePercentage
                + ", maskedCardNumber4Digits=" + maskedCardNumber4Digits + "]";
    }

}
