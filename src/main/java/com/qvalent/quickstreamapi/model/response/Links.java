package com.qvalent.quickstreamapi.model.response;

import java.util.ArrayList;

public class Links extends ArrayList<Link>
{
    private static final long serialVersionUID = 1L;

    public Link getLink( final String rel )
    {
        return stream()
                .filter( link -> link.getRel().equals( rel ) )
                .findFirst()
                .orElse( null );
    }
}
