package com.qvalent.quickstreamapi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.qvalent.quickstreamapi.model.response.ApiKey;
import com.qvalent.quickstreamapi.model.response.Result;

public class ApiKeysAPITest
{
    private QuickstreamAPI quickstreamAPI;

    @Before
    public void before()
    {
        quickstreamAPI = new QuickstreamAPI(
            Environment.PRODUCTION,
            "QUICKSTREAMDEMO_PUB",
            "QUICKSTREAMDEMO_SEC"
        );
    }

    @Test
    public void getLatestApiKey()
    {
        final Result<ApiKey> result = quickstreamAPI.apiKeys().getLatest();
        assertEquals( "QUICKSTREAMDEMO_SEC", result.getTarget().getKeyName() );
    }
}
