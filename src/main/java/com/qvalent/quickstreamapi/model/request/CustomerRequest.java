package com.qvalent.quickstreamapi.model.request;

import com.qvalent.quickstreamapi.model.common.Status;
import com.qvalent.quickstreamapi.model.response.Customer;
import com.qvalent.quickstreamapi.model.response.NotificationMedium;

public class CustomerRequest extends Request
{
    private final String customerNumber;
    private final String customerName;
    private final String supplierBusinessCode;
    private final String phoneNumber;
    private final String emailAddress;
    private final NotificationMedium preferredNotificationMedium;
    private final AddressRequest address;
    private final boolean enabled;

    public CustomerRequest( final CustomerRequestBuilder builder )
    {
        customerNumber = builder.customerNumber;
        customerName = builder.customerName;
        supplierBusinessCode = builder.supplierBusinessCode;
        phoneNumber = builder.phoneNumber;
        emailAddress = builder.emailAddress;
        preferredNotificationMedium = builder.preferredNotificationMedium;
        address = builder.address;
        enabled = builder.enabled;
    }

    public boolean getEnabled()
    {
        return enabled;
    }

    public String getCustomerNumber()
    {
        return customerNumber;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public String getSupplierBusinessCode()
    {
        return supplierBusinessCode;
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

    public AddressRequest getAddress()
    {
        return address;
    }

    public static class CustomerRequestBuilder
    {
        private String customerNumber;
        private String customerName;
        private String supplierBusinessCode;
        private String phoneNumber;
        private String emailAddress;
        private NotificationMedium preferredNotificationMedium;
        private AddressRequest address;
        private boolean enabled;

        public CustomerRequestBuilder()
        {
        }

        public CustomerRequestBuilder( final Customer customer )
        {
            customerNumber = customer.getCustomerNumber();
            customerName = customer.getCustomerName();
            supplierBusinessCode = customer.getSupplierBusinessCode();
            phoneNumber = customer.getPhoneNumber();
            emailAddress = customer.getEmailAddress();
            preferredNotificationMedium = customer.getPreferredNotificationMedium();
            address = new AddressRequest( customer.getAddress() );
            enabled = customer.getStatus() == Status.ENABLED ? true : false;

        }

        public CustomerRequestBuilder enabled( final boolean enabled )
        {
            this.enabled = enabled;
            return this;
        }

        public CustomerRequestBuilder customerNumber( final String customerNumber )
        {
            this.customerNumber = customerNumber;
            return this;
        }

        public CustomerRequestBuilder customerName( final String customerName )
        {
            this.customerName = customerName;
            return this;
        }

        public CustomerRequestBuilder supplierBusinessCode( final String supplierBusinessCode )
        {
            this.supplierBusinessCode = supplierBusinessCode;
            return this;
        }

        public CustomerRequestBuilder phoneNumber( final String phoneNumber )
        {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CustomerRequestBuilder emailAddress( final String emailAddress )
        {
            this.emailAddress = emailAddress;
            return this;
        }

        public CustomerRequestBuilder preferredNotificationMedium( final NotificationMedium preferredNotificationMedium )
        {
            this.preferredNotificationMedium = preferredNotificationMedium;
            return this;
        }

        public CustomerRequestBuilder address( final AddressRequest address )
        {
            this.address = address;
            return this;
        }

        public CustomerRequest build()
        {
            return new CustomerRequest( this );
        }
    }
}
