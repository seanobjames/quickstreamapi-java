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

    private String myEnvironmentName;
    private String myBaseURL;

    // For internal QuickStream development
    public static final Environment DEVELOPMENT = new Environment( getDevelopmentBaseURL() + ":" + getDevelopmentPort(),
            "development" );

    // For you to use during your development and testing.
    public static final Environment TEST = new Environment( "https://api.quickstream.support.qvalent.com", "test" );

    // For production.
    public static final Environment PRODUCTION = new Environment( "https://api.quickstream.westpac.com.au", "production" );

    public Environment( final String baseURL, final String environmentName )
    {
        myEnvironmentName = environmentName;
        myBaseURL = baseURL;
    }

    public String getEnvironmentName()
    {
        return myEnvironmentName;
    }

    public void setEnvironmentName( String environmentName )
    {
        this.myEnvironmentName = environmentName;
    }

    public String getBaseURL()
    {
        return myBaseURL;
    }

    public void setBaseURL( String baseURL )
    {
        this.myBaseURL = baseURL;
    }

    public static String getDevelopmentBaseURL()
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

    public static String getDevelopmentPort()
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
        return "Environment [environmentName=" + myEnvironmentName + ", baseURL=" + myBaseURL + "]";
    }
}