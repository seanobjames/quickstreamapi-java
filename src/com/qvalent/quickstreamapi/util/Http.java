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
import org.json.JSONObject;

import com.qvalent.quickstreamapi.Configuration;
import com.qvalent.quickstreamapi.Resource;
import com.qvalent.quickstreamapi.exception.AuthenticationException;
import com.qvalent.quickstreamapi.exception.AuthorizationException;
import com.qvalent.quickstreamapi.exception.UnexpectedException;
import com.qvalent.quickstreamapi.model.ErrorResponse;

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

    public JSONObject get( final AccessType accessType, final String url )
    {
        return httpRequest( RequestMethod.GET, accessType, url, null );
    }

    public JSONObject delete( final AccessType accessType, final String url )
    {
        return httpRequest( RequestMethod.DELETE, accessType, url, null );
    }

    public JSONObject post( final AccessType accessType, final String url )
    {
        return httpRequest( RequestMethod.POST, accessType, url, null );
    }

    public JSONObject post( final AccessType accessType, final String url, final Resource resource )
    {
        return httpRequest( RequestMethod.POST, accessType, url, resource.toJSON() );
    }

    public JSONObject put( final AccessType accessType, final String url )
    {
        return httpRequest( RequestMethod.PUT, accessType, url, null );
    }

    public JSONObject put( final AccessType accessType, final String url, final Resource resource )
    {
        return httpRequest( RequestMethod.PUT, accessType, url, resource.toJSON() );
    }

    public JSONObject patch( final AccessType accessType, final String url )
    {
        return httpRequest( RequestMethod.PATCH, accessType, url, null );
    }

    public JSONObject patch( final AccessType accessType, final String url, final Resource resource )
    {
        return httpRequest( RequestMethod.PATCH, accessType, url, resource.toJSON() );
    }

    private JSONObject httpRequest( final RequestMethod requestMethod,
                                    final AccessType accessType,
                                    final String url,
                                    final String postBody )
    {
        HttpURLConnection connection = null;
        final String contentType = "application/json";
        JSONObject jsonResponse = null;

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

            try ( InputStream inputStream = (connection.getResponseCode() == 422) ? connection.getErrorStream() : connection.getInputStream() )
            {
                final String response = StringUtil.inputStreamToString( inputStream );

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

                jsonResponse = new JSONObject( response );
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

        return jsonResponse;
    }

    private void throwExceptionIfErrorResponseReceived( final HttpURLConnection connection ) throws IOException
    {
        if( isErrorCode( connection.getResponseCode() ) )
        {
            ErrorResponse error = null;
            try( InputStream inputStream = connection.getErrorStream() )
            {
                error = ErrorResponse.from( new JSONObject( StringUtil.inputStreamToString( inputStream ) ) );
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
                                               final String contentType ) throws java.io.IOException
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

    private String sanitiseRequestBodyForLogging( String requestBody )
    {
        if ( requestBody == null )
        {
            return requestBody;
        }

        Pattern regex = Pattern.compile( "(^)", Pattern.MULTILINE );
        Matcher matcher = regex.matcher( requestBody );
        if ( matcher.find() )
        {
            requestBody = matcher.replaceAll( Configuration.theLogPrefix + " $1" );
        }

        regex = Pattern.compile( "(.{6}).+?(.{4})" );
        matcher = regex.matcher( requestBody );
        if ( matcher.find() )
        {
            requestBody = matcher.replaceAll( "$1xxxxxx$2" );
        }

        return requestBody;
    }
}