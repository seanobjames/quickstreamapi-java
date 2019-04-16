package com.qvalent.quickstreamapi;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import com.qvalent.quickstreamapi.exception.ConfigurationException;

public class Configuration
{
    private static int theDefaultTimeout = 6000;

    private final Environment environment;
    private String publishableKey;
    private String secretKey;
    private int timeout;
    private Proxy proxy;

    private static Logger theLogger;
    private static String theLogName = "QuickstreamAPI";
    public static String theLogPrefix = "[" + theLogName + "]";

    static
    {
        theLogger = Logger.getLogger( theLogName );
        theLogger.setLevel( Level.INFO );
    }

    public static String clientLibraryVersion()
    {
        return "0.1";
    }

    public static String apiVersion()
    {
        return "1";
    }

    public Configuration( final Environment environment, final String publishableKey, final String secretKey )
    {
        this.environment = environment;

        if ( StringUtils.isEmpty( publishableKey ) )
        {
            throw new ConfigurationException( "publishableKey must be set" );
        }
        else
        {
            this.publishableKey = publishableKey;
        }

        if ( StringUtils.isEmpty( secretKey ) )
        {
            throw new ConfigurationException( "secretKey must be set" );
        }
        else
        {
            this.secretKey = secretKey;
        }
    }

    public String getBaseURL()
    {
        return environment.getBaseURL();
    }

    public Boolean usesProxy()
    {
        return proxy != null;
    }

    public Proxy getProxy()
    {
        return proxy;
    }

    public void setProxy( final String url, final Integer port )
    {
        proxy = new Proxy( Proxy.Type.HTTP, new InetSocketAddress( url, port ) );
    }

    public void setProxy( final Proxy proxy )
    {
        this.proxy = proxy;
    }

    public Logger getLogger()
    {
        return theLogger;
    }

    public void setLogger( final Logger logger )
    {
        theLogger = logger;
    }

    public int getTimeout()
    {
        return ( timeout == 0 ) ? theDefaultTimeout : timeout;
    }

    public void setTimeout( final int timeout )
    {
        this.timeout = timeout;
    }

    public String getPublishableKey()
    {
        return publishableKey;
    }
    public String getSecretKey()
    {
        return secretKey;
    }
}
