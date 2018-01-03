package com.qvalent.quickstreamapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

import org.junit.Test;

public class ConfigurationTest
{
    @Test
    public void defaultConfigurationCreatedSuccess()
    {
        final Configuration configuration = new Configuration(
                Environment.TEST,
                "thePublishableKey",
                "theSecretKey" );

        assertEquals( configuration.getTimeout(), 6000 );
        assertEquals( configuration.getPublishableKey(), "thePublishableKey" );
        assertEquals( configuration.getSecretKey(), "theSecretKey" );
        assertNotNull( configuration.getLogger() );
        assertNull( configuration.getProxy() );

        configuration.setProxy( "http://proxy.myurl.com", 8000 );
        assertThat( configuration.getProxy().toString(), containsString( "http://proxy.myurl.com:8000" ) );

        assertEquals( Configuration.theLogPrefix, "[QuickstreamAPI]" );
        assertEquals( Configuration.apiVersion(), "1" );
        assertEquals( Configuration.clientLibraryVersion(), "0.1" );
    }

    @Test
    public void testConfigurationCreatedEnvironmentURLCorrect()
    {
        final Configuration configuration = new Configuration(
                Environment.TEST,
                "thePublishableKey",
                "theSecretKey" );
        assertEquals( configuration.getBaseURL(), "https://api.quickstream.support.qvalent.com/rest/v1" );
    }

    @Test
    public void prodConfigurationCreatedEnvironmentURLCorrect()
    {
        final Configuration configuration = new Configuration(
                Environment.PRODUCTION,
                "thePublishableKey",
                "theSecretKey" );
        assertEquals( configuration.getBaseURL(), "https://api.quickstream.westpac.com.au/rest/v1" );
    }

    @Test
    public void decConfigurationCreatedEnvironmentURLCorrect()
    {
        final Configuration configuration = new Configuration(
                Environment.DEVELOPMENT,
                "thePublishableKey",
                "theSecretKey" );
        assertEquals( configuration.getBaseURL(), "http://localhost:7001/rest/v1" );
    }
}
