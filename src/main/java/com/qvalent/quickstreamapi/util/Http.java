package com.qvalent.quickstreamapi.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.qvalent.quickstreamapi.Configuration;
import com.qvalent.quickstreamapi.exception.AuthenticationException;
import com.qvalent.quickstreamapi.exception.AuthorizationException;
import com.qvalent.quickstreamapi.exception.UnexpectedException;
import com.qvalent.quickstreamapi.model.request.Request;
import com.qvalent.quickstreamapi.model.response.Error;

public class Http
{
    public enum RequestMethod
    {
        GET, DELETE, POST, PUT, PATCH;
    }

    public enum AccessType
    {
        PUBLISHABLE_KEY, SECRET_KEY;
    }

    private final Configuration myConfiguration;

    public Http( final Configuration configuration )
    {
        myConfiguration = configuration;
    }

    public String get( final AccessType accessType, final String url )
    {
        return httpRequest( RequestMethod.GET, accessType, url, null );
    }

    public String delete( final AccessType accessType, final String url )
    {
        return httpRequest( RequestMethod.DELETE, accessType, url, null );
    }

    public String post( final AccessType accessType, final String url )
    {
        return httpRequest( RequestMethod.POST, accessType, url, null );
    }

    public String post( final AccessType accessType, final String url, final Request request )
    {
        return httpRequest( RequestMethod.POST, accessType, url, request.toJSON() );
    }

    public String put( final AccessType accessType, final String url )
    {
        return httpRequest( RequestMethod.PUT, accessType, url, null );
    }

    public String put( final AccessType accessType, final String url, final Request request )
    {
        return httpRequest( RequestMethod.PUT, accessType, url, request.toJSON() );
    }

    public String patch( final AccessType accessType, final String url )
    {
        return httpRequest( RequestMethod.PATCH, accessType, url, null );
    }

    public String patch( final AccessType accessType, final String url, final Request request )
    {
        return httpRequest( RequestMethod.PATCH, accessType, url, request.toJSON() );
    }

    private String httpRequest( final RequestMethod requestMethod,
                                final AccessType accessType,
                                final String url,
                                final String postBody )
    {
        HttpURLConnection connection = null;
        final String contentType = "application/json";
        String response = null;

        try
        {
            connection = buildConnection( requestMethod, accessType, url, contentType );
            final Logger logger = myConfiguration.getLogger();
            if ( postBody != null )
            {
                logger.log( Level.FINE, sanitiseRequestBodyForLogging( postBody ) );
            }

            if ( postBody != null )
            {
                OutputStream outputStream = null;
                try
                {
                    outputStream = connection.getOutputStream();
                    outputStream.write( postBody.getBytes("UTF-8") );
                }
                finally
                {
                    if ( outputStream != null )
                    {
                        outputStream.close();
                    }
                }
            }

            throwExceptionIfErrorResponseReceived( connection );

            try ( InputStream inputStream =
                    (connection.getResponseCode() == 422) ? connection.getErrorStream() : connection.getInputStream() )
            {
                response = StringUtil.inputStreamToString( inputStream );

                logger.log(
                        Level.INFO,
                        Configuration.theLogPrefix + " [{0}]] {1} {2}",
                        new Object[] { getCurrentTime(), requestMethod.toString(), url } );
                logger.log(
                        Level.FINE,
                        Configuration.theLogPrefix + " [{0}]] {1} {2} {3}",
                        new Object[] { getCurrentTime(), requestMethod.toString(), url, connection.getResponseCode() } );

                if( response != null )
                {
                    logger.log( Level.FINE, sanitiseRequestBodyForLogging( response ) );
                }

                if( StringUtils.isEmpty( response ) )
                {
                    return null;
                }
            }
        }
        catch( final IOException e )
        {
            throw new UnexpectedException( e.getMessage(), e );
        }
        finally
        {
            if( connection != null )
            {
                connection.disconnect();
            }
        }

        return response;
    }

    private void throwExceptionIfErrorResponseReceived( final HttpURLConnection connection ) throws IOException
    {
        if( isErrorCode( connection.getResponseCode() ) )
        {
            Error error = null;
            try( InputStream inputStream = connection.getErrorStream() )
            {
                error = Error.from( StringUtil.inputStreamToString( inputStream ) );
            }

            switch( connection.getResponseCode() )
            {
                // TODO other exception types for common error responses
                case 401 :
                    throw new AuthenticationException( error );
                case 403 :
                    throw new AuthorizationException( error );
                default:
                    throw new UnexpectedException(
                            "Unexpected HTTP Response " + connection.getResponseCode(), error );
            }
        }
    }

    private HttpURLConnection buildConnection( final RequestMethod requestMethod,
                                               final AccessType accessType,
                                               final String urlString,
                                               final String contentType ) throws IOException
    {
        final URL url = new URL(myConfiguration.getBaseURL() + urlString);
        HttpURLConnection connection;
        if ( myConfiguration.usesProxy() )
        {
            connection = (HttpURLConnection)url.openConnection( myConfiguration.getProxy() );
        }
        else
        {
            connection = (HttpURLConnection)url.openConnection();
        }

        connection.setRequestMethod( requestMethod.toString() );
        connection.addRequestProperty( "Accept", "application/json" );
        connection.addRequestProperty(
                "User-Agent",
                "QuickStream REST API - Java Client Library " + Configuration.clientLibraryVersion() );
        connection.addRequestProperty( "Authorization", getAuthorizationHeader( accessType ) );
        connection.setRequestProperty( "Content-Type", contentType );

        connection.setDoOutput( true );
        connection.setReadTimeout( myConfiguration.getTimeout() );
        return connection;
    }

    private String getAuthorizationHeader( final AccessType accessType )
    {
        final String accessKey = ( accessType == AccessType.PUBLISHABLE_KEY )
                ? myConfiguration.getPublishableKey() : myConfiguration.getSecretKey();

        return "Basic " + Base64.getEncoder().encodeToString( accessKey.getBytes() ).trim();
    }

    private String getCurrentTime()
    {
        return new SimpleDateFormat( "d/MMM/yyyy HH:mm:ss Z" ).format( new Date() );
    }

    private boolean isErrorCode( final int responseCode )
    {
        return responseCode != 200 && responseCode != 201 && responseCode != 422;
    }

    private String sanitiseRequestBodyForLogging( final String requestBody )
    {
        if ( requestBody == null )
        {
            return requestBody;
        }

        String sanitisedRequestBody = requestBody;

        Pattern regex = Pattern.compile( "(^)", Pattern.MULTILINE );
        Matcher matcher = regex.matcher( sanitisedRequestBody );
        if ( matcher.find() )
        {
            sanitisedRequestBody = matcher.replaceAll( Configuration.theLogPrefix + " $1" );
        }

        regex = Pattern.compile( "(.{6}).+?(.{4})" );
        matcher = regex.matcher( sanitisedRequestBody );
        if ( matcher.find() )
        {
            sanitisedRequestBody = matcher.replaceAll( "$1xxxxxx$2" );
        }

        return sanitisedRequestBody;
    }
}