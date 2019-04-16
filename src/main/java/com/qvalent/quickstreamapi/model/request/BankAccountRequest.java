package com.qvalent.quickstreamapi.model.request;

import com.qvalent.quickstreamapi.model.common.AccountType;

public class BankAccountRequest extends SingleUseTokenRequest
{
    private final String accountName;
    private final String bsb;
    private final String accountNumber;

    private BankAccountRequest( final BankAccountRequestBuilder builder )
    {
        super( builder.supplierBusinessCode, AccountType.DIRECT_DEBIT );
        accountName = builder.accountName;
        bsb = builder.bsb;
        accountNumber = builder.accountNumber;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public String getBsb()
    {
        return bsb;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public static class BankAccountRequestBuilder
    {
        private final String supplierBusinessCode;
        private String accountName;
        private String bsb;
        private String accountNumber;

        public BankAccountRequestBuilder( final String supplierBusinessCode )
        {
            this.supplierBusinessCode = supplierBusinessCode;
        }

        public BankAccountRequestBuilder accountName( final String accountName )
        {
            this.accountName = accountName;
            return this;
        }

        public BankAccountRequestBuilder bsb( final String bsb )
        {
            this.bsb = bsb;
            return this;
        }

        public BankAccountRequestBuilder accountNumber( final String accountNumber )
        {
            this.accountNumber = accountNumber;
            return this;
        }

        public BankAccountRequest build()
        {
            return new BankAccountRequest( this );
        }
    }
}
