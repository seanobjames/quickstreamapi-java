package com.qvalent.quickstreamapi.model.request;

public class CardRequest extends Request
{
    private final String supplierBusinessCode;
    private final AccountType accountType;
    private final String cardholderName;
    private final String cardNumber;
    private final String expiryDateMonth;
    private final String expiryDateYear;
    private final String cvn;

    private CardRequest( final CardRequestBuilder builder )
    {
        supplierBusinessCode = builder.supplierBusinessCode;
        accountType = AccountType.CREDIT_CARD;
        cardholderName = builder.cardholderName;
        cardNumber = builder.cardNumber;
        expiryDateMonth = builder.expiryDateMonth;
        expiryDateYear = builder.expiryDateYear;
        cvn = builder.cvn;
    }

    public String getSupplierBusinessCode()
    {
        return supplierBusinessCode;
    }

    public AccountType getAccountType()
    {
        return accountType;
    }

    public String getCardholderName()
    {
        return cardholderName;
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

    public String getCvn()
    {
        return cvn;
    }

    public static class CardRequestBuilder
    {
        private final String supplierBusinessCode;
        private String cardNumber;
        private String expiryDateMonth;
        private String expiryDateYear;
        private String cardholderName;
        private String cvn;

        public CardRequestBuilder( final String supplierBusinessCode )
        {
            this.supplierBusinessCode = supplierBusinessCode;
        }

        public CardRequestBuilder cardholderName( final String cardholderName )
        {
            this.cardholderName = cardholderName;
            return this;
        }

        public CardRequestBuilder cardNumber( final String cardNumber )
        {
            this.cardNumber = cardNumber;
            return this;
        }

        public CardRequestBuilder cvn( final String cvn )
        {
            this.cvn = cvn;
            return this;
        }

        public CardRequestBuilder expiryDateMonth( final String expiryDateMonth )
        {
            this.expiryDateMonth = expiryDateMonth;
            return this;
        }

        public CardRequestBuilder expiryDateYear( final String expiryDateYear )
        {
            this.expiryDateYear = expiryDateYear;
            return this;
        }

        public CardRequest build()
        {
            return new CardRequest( this );
        }
    }
}
