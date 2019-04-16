package com.qvalent.quickstreamapi.model.response;

import java.util.ArrayList;

public class BusinessCardScheme
{
    private final ArrayList<Link> links;
    private final CardScheme cardScheme;

    public BusinessCardScheme()
    {
        links = null;
        cardScheme = null;
    }

    public ArrayList<Link> getLinks()
    {
        return links;
    }

    public CardScheme getCardScheme()
    {
        return cardScheme;
    }

    @Override
    public String toString()
    {
        return "BusinessCardScheme [links=" + links
                + ", cardScheme=" + cardScheme + "]";
    }
}
