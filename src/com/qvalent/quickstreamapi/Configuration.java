package com.qvalent.quickstreamapi;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.qvalent.quickstreamapi.exception.ConfigurationException;

public class Configuration
{
    private Environment myEnvironment;
    private String myPublishableKey;
    private String mySecretKey;
    private static Logger theLogger;
    private int myTimeout;
    private Proxy myProxy;

    static
    {
        theLogger = Logger.getLogger( "QuickStreamAPI" );
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

        if ( publishableKey == null || publishableKey.isEmpty() )
        {
            throw new ConfigurationException( "publishableKey must be set" );
        } else
        {
            myPublishableKey = publishableKey;
        }

        if ( secretKey == null || secretKey.isEmpty() )
        {
            throw new ConfigurationException( "secretKey must be set" );
        } else
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
}
