package com.qvalent.quickstreamapi;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import com.qvalent.quickstreamapi.exception.ConfigurationException;

public class Configuration
{
    private Environment myEnvironment;
    private String myPublishableKey;
    private String mySecretKey;
    private int myTimeout;
    private Proxy myProxy;
    
    private static Logger theLogger;
    private static String theLogName = "QuickStreamAPI";
    public static String theLogPrefix = "[" + theLogName + "]";

    static
    {
        theLogger = Logger.getLogger( theLogName );
        theLogger.setLevel( Level.INFO );
    }

    public static String clientLibraryVersion()
    {
        // TODO read from a properties file set on the build server.
        return "0.1";
    }

    public static String apiVersion()
    {
        return "1";
    }

    private static int theDefaultTimeout = 6000;

    public Configuration( final Environment environment, final String publishableKey, final String secretKey )
    {
        myEnvironment = environment;

        if ( StringUtils.isEmpty( publishableKey ) )
        {
            throw new ConfigurationException( "publishableKey must be set" );
        }
        else
        {
            myPublishableKey = publishableKey;
        }

        if ( StringUtils.isEmpty( secretKey ) )
        {
            throw new ConfigurationException( "secretKey must be set" );
        }
        else
        {
            mySecretKey = secretKey;
        }
    }

    public String getBaseURL()
    {
        return myEnvironment.getBaseURL();
    }

    public Boolean usesProxy()
    {
        return myProxy != null;
    }
    
    public Proxy getProxy()
    {
        return myProxy;
    }

    public void setProxy( final String url, final Integer port )
    {
        myProxy = new Proxy( Proxy.Type.HTTP, new InetSocketAddress( url, port ) );
    }

    public void setProxy( final Proxy proxy )
    {
        myProxy = proxy;
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
        return ( myTimeout == 0 ) ? theDefaultTimeout : myTimeout;
    }

    public void setTimeout( int timeout )
    {
        myTimeout = timeout;
    }

    public String getPublishableKey()
    {
        return myPublishableKey;
    }
    public String getSecretKey()
    {
        return mySecretKey;
    }
}
