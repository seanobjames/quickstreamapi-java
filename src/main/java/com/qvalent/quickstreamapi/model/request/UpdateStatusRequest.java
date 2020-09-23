package com.qvalent.quickstreamapi.model.request;

public class UpdateStatusRequest extends Request
{
    private final boolean enabled;

    public UpdateStatusRequest( final UpdateStatusRequestBuilder builder )
    {
        enabled = builder.enabled;
    }

    public boolean getEnabled()
    {
        return enabled;
    }

    public static class UpdateStatusRequestBuilder
    {
        private final boolean enabled;

        public UpdateStatusRequestBuilder( final boolean enabled )
        {
            this.enabled = enabled;
        }

        public UpdateStatusRequest build()
        {
            return new UpdateStatusRequest( this );
        }
    }
}
