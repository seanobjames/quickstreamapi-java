package com.qvalent.quickstreamapi.model.response;

import com.qvalent.quickstreamapi.model.common.AccountType;

public class SingleUseToken
{
    private final String singleUseTokenId;
    private final Links links;
    private final AccountType accountType;
    private final CreditCard creditCard;
    private final BankAccount bankAccount;

    public SingleUseToken()
    {
        singleUseTokenId = null;
        links = null;
        accountType = null;
        creditCard = null;
        bankAccount = null;
    }

    public String getSingleUseTokenId()
    {
        return singleUseTokenId;
    }

    public Links getLinks()
    {
        return links;
    }

    public AccountType getAccountType()
    {
        return accountType;
    }

    public CreditCard getCreditCard()
    {
        return creditCard;
    }

    public BankAccount getBankAccount()
    {
        return bankAccount;
    }

    @Override
    public String toString()
    {
        return "SingleUseToken [singleUseTokenId=" + singleUseTokenId
                + ", links=" + links
                + ", accountType=" + accountType
                + ", creditCard=" + creditCard
                + ", bankAccount=" + bankAccount + "]";
    }


}
