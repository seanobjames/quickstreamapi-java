package com.qvalent.quickstreamapi.model.response;

import java.util.ArrayList;

public class Business
{
    private final ArrayList<Link> links;
    private final String supplierBusinessName;
    private final String supplierBusinessCode;
    private final String registeredName;
    private final String abn;
    private final String contactName;
    private final String contactEmail;
    private final String contactPhone;
    private final String customerNotificationEmail;
    private final Address businessAddress;

    public Business()
    {
        links = null;
        supplierBusinessName = null;
        supplierBusinessCode = null;
        registeredName = null;
        abn = null;
        contactName = null;
        contactEmail = null;
        contactPhone = null;
        customerNotificationEmail = null;
        businessAddress = null;
    }

    public ArrayList<Link> getLinks()
    {
        return links;
    }

    public String getSupplierBusinessName()
    {
        return supplierBusinessName;
    }

    public String getSupplierBusinessCode()
    {
        return supplierBusinessCode;
    }

    public String getRegisteredName()
    {
        return registeredName;
    }

    public String getAbn()
    {
        return abn;
    }

    public String getContactName()
    {
        return contactName;
    }

    public String getContactEmail()
    {
        return contactEmail;
    }

    public String getContactPhone()
    {
        return contactPhone;
    }

    public String getCustomerNotificationEmail()
    {
        return customerNotificationEmail;
    }

    public Address getBusinessAddress()
    {
        return businessAddress;
    }

    @Override
    public String toString()
    {
        return "Business [links=" + links
                + ", supplierBusinessName=" + supplierBusinessName + ", supplierBusinessCode=" + supplierBusinessCode
                + ", registeredName=" + registeredName + ", abn=" + abn + ", contactName=" + contactName
                + ", contactEmail=" + contactEmail
                + ", contactPhone=" + contactPhone
                + ", customerNotificationEmail=" + customerNotificationEmail
                + ", businessAddress=" + businessAddress + "]";
    }
}
