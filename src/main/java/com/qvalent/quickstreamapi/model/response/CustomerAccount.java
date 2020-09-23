package com.qvalent.quickstreamapi.model.response;

import com.qvalent.quickstreamapi.model.common.AccountType;

public abstract class CustomerAccount
{
    private final AccountType accountType;
    private final String accountToken;
    private final Boolean defaultAccount;
    private final Links links;

    public CustomerAccount()
    {
        accountType = null;
        accountToken = null;
        defaultAccount = null;
        links = null;
    }

    public AccountType getAccountType()
    {
        return accountType;
    }

    public String getAccountToken()
    {
        return accountToken;
    }

    public Boolean getDefaultAccount()
    {
        return defaultAccount;
    }

    public Links getLinks()
    {
        return links;
    }

    @Override
    public String toString()
    {
        return "CustomerAccount [accountType=" + accountType
                + ", accountToken=" + accountToken
                + ", defaultAccount=" + defaultAccount
                + ", links=" + links + "]";
    }
}
