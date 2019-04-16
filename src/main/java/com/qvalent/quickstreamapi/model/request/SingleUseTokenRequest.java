package com.qvalent.quickstreamapi.model.request;

import com.qvalent.quickstreamapi.model.common.AccountType;

public abstract class SingleUseTokenRequest extends Request
{
    private final String supplierBusinessCode;
    private final AccountType accountType;

    public SingleUseTokenRequest( final String supplierBusinessCode,
                                  final AccountType accountType )
    {
        this.supplierBusinessCode = supplierBusinessCode;
        this.accountType = accountType;
    }

    public String getSupplierBusinessCode()
    {
        return supplierBusinessCode;
    }

    public AccountType getAccountType()
    {
        return accountType;
    }
}
