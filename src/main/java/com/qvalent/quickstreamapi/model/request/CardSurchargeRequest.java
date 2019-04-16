package com.qvalent.quickstreamapi.model.request;

public class CardSurchargeRequest extends Request
{
    private final String cardNumber;

    public CardSurchargeRequest( final CardSurchargeRequestBuilder builder )
    {
        cardNumber = builder.cardNumber;
    }

    public String getCardNumber()
    {
        return cardNumber;
    }

    public static class CardSurchargeRequestBuilder
    {
        private String cardNumber;

        public CardSurchargeRequestBuilder()
        {
            cardNumber = null;
        }

        public CardSurchargeRequestBuilder cardNumber( final String cardNumber )
        {
            this.cardNumber = cardNumber;
            return this;
        }

        public CardSurchargeRequest build()
        {
            return new CardSurchargeRequest( this );
        }
    }
}
