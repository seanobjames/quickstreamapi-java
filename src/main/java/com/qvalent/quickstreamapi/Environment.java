package com.qvalent.quickstreamapi;

/**
 * The environment of the QuickStream REST API to interact with.
 */
public class Environment
{
    private static final String theDefaultDevelopmentBaseURL = "http://localhost";
    private static final String theDefaultDevelopmentPort = "7001";
    private static final String theDevelopmentBaseURLSysEnvName = "DEV_BASE_URL";
    private static final String theDevelopmentPortSysEnvName = "DEV_PORT";

    private String environmentName;
    private String baseURL;

    // For internal QuickStream development
    public static final Environment DEVELOPMENT = new Environment( getDevBaseURL() + ":" + getDevPort(), "development" );

    // For you to use during your development and testing.
    public static final Environment TEST = new Environment( "https://api.quickstream.support.qvalent.com", "test" );

    // For production.
    public static final Environment PRODUCTION = new Environment( "https://api.quickstream.westpac.com.au", "production" );

    public Environment( final String baseURL, final String environmentName )
    {
        this.environmentName = environmentName;
        this.baseURL = baseURL + "/rest/v" + Configuration.apiVersion();
    }

    public String getEnvironmentName()
    {
        return environmentName;
    }

    public void setEnvironmentName( final String environmentName )
    {
        this.environmentName = environmentName;
    }

    public String getBaseURL()
    {
        return baseURL;
    }

    public void setBaseURL( final String baseURL )
    {
        this.baseURL = baseURL;
    }

    public static String getDevBaseURL()
    {
        if ( System.getenv().get( theDevelopmentBaseURLSysEnvName ) != null )
        {
            return System.getenv().get( theDevelopmentBaseURLSysEnvName );
        }
        else
        {
            return theDefaultDevelopmentBaseURL;
        }
    }

    public static String getDevPort()
    {
        if ( System.getenv().get( theDevelopmentPortSysEnvName ) != null )
        {
            return System.getenv().get( theDevelopmentPortSysEnvName );
        }
        else
        {
            return theDefaultDevelopmentPort;
        }
    }

    @Override
    public String toString()
    {
        return "Environment [environmentName=" + environmentName + ", baseURL=" + baseURL + "]";
    }
}