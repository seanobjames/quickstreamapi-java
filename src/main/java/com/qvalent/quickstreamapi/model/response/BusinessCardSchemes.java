package com.qvalent.quickstreamapi.model.response;

import java.util.ArrayList;

public class BusinessCardSchemes
{
    private final Links links;
    private final ArrayList<BusinessCardScheme> data;

    public BusinessCardSchemes()
    {
        links = null;
        data = null;
    }

    public Links getLinks()
    {
        return links;
    }

    public ArrayList<BusinessCardScheme> getData()
    {
        return data;
    }

    @Override
    public String toString()
    {
        return "AcceptedCards [links=" + links
                + ", data=" + data + "]";
    }
}
