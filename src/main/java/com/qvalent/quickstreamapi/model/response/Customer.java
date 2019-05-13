package com.qvalent.quickstreamapi.model.response;

import com.qvalent.quickstreamapi.model.common.Status;

public class Customer
{
    private final String customerId;
    private final String customerName;
    private final String supplierBusinessCode;
    private final String customerNumber;
    private final Status status;
    private final String phoneNumber;
    private final String emailAddress;
    private final NotificationMedium preferredNotificationMedium;
    private final Address address;
    private final Links links;

    public Customer()
    {
        customerId = null;
        customerName = null;
        supplierBusinessCode = null;
        customerNumber = null;
        status = null;
        phoneNumber = null;
        emailAddress = null;
        preferredNotificationMedium = null;
        address = null;
        links = null;
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public String getSupplierBusinessCode()
    {
        return supplierBusinessCode;
    }

    public String getCustomerNumber()
    {
        return customerNumber;
    }

    public Status getStatus()
    {
        return status;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public NotificationMedium getPreferredNotificationMedium()
    {
        return preferredNotificationMedium;
    }

    public Address getAddress()
    {
        return address;
    }

    public Links getLinks()
    {
        return links;
    }

    @Override
    public String toString()
    {
        return "Customer [customerId=" + customerId
                + ", customerName=" + customerName
                + ", supplierBusinessCode=" + supplierBusinessCode
                + ", customerNumber=" + customerNumber
                + ", status=" + status + ", phoneNumber=" + phoneNumber
                + ", emailAddress=" + emailAddress
                + ", preferredNotificationMedium=" + preferredNotificationMedium
                + ", address=" + address
                + ", links=" + links + "]";
    }
}
