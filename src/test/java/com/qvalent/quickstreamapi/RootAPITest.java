package com.qvalent.quickstreamapi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.qvalent.quickstreamapi.model.response.Result;
import com.qvalent.quickstreamapi.model.response.Root;

public class RootAPITest
{
    private QuickstreamAPI quickstreamAPI;

    @Before
    public void before()
    {
        quickstreamAPI = new QuickstreamAPI();
    }

    @Test
    public void getRootSuccess()
    {
        final Result<Root> result = quickstreamAPI.root().get();
        assertEquals( "QUICKSTREAMDEMO", result.getTarget().getCommunityCode() );
    }
}
