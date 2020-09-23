package com.qvalent.quickstreamapi.model.response;

public class BankAccount extends CustomerAccount
{
    private final String accountName;
    private final String bsb;
    private final String accountNumber;

    public BankAccount()
    {
        accountName = null;
        bsb = null;
        accountNumber = null;
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

    @Override
    public String toString()
    {
        return "BankAccount [accountName=" + accountName
                + ", bsb=" + bsb
                + ", accountNumber=" + accountNumber + "]"
                + ", " + super.toString();
    }
}
