package com.qvalent.quickstreamapi.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
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
import com.qvalent.quickstreamapi.exception.DownForMaintenanceException;
import com.qvalent.quickstreamapi.exception.ServerException;
import com.qvalent.quickstreamapi.exception.TimeoutException;
import com.qvalent.quickstreamapi.exception.TooManyRequestsException;
import com.qvalent.quickstreamapi.exception.UnexpectedException;
import com.qvalent.quickstreamapi.model.request.Request;
import com.qvalent.quickstreamapi.model.response.Link;
import com.qvalent.quickstreamapi.model.response.ResponseWrapper;
import com.qvalent.quickstreamapi.model.response.ValidationErrors;

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

    public ResponseWrapper get( final AccessType accessType, final String url )
    {
        return httpRequest( RequestMethod.GET, accessType, getURL( url ), null );
    }

    public ResponseWrapper get( final AccessType accessType, final Link link )
    {
        return httpRequest( RequestMethod.GET, accessType, link.getHref(), null );
    }

    public ResponseWrapper delete( final AccessType accessType, final String url )
    {
        return httpRequest( RequestMethod.DELETE, accessType, getURL( url ), null );
    }

    public ResponseWrapper post( final AccessType accessType, final String url )
    {
        return httpRequest( RequestMethod.POST, accessType, getURL( url ), null );
    }

    public ResponseWrapper post( final AccessType accessType, final String url, final Request request )
    {
        return httpRequest( RequestMethod.POST, accessType, getURL( url ), request.toJSON() );
    }

    public ResponseWrapper put( final AccessType accessType, final String url ) throws MalformedURLException
    {
        return httpRequest( RequestMethod.PUT, accessType, getURL( url ), null );
    }

    public ResponseWrapper put( final AccessType accessType, final String url, final Request request )
    {
        return httpRequest( RequestMethod.PUT, accessType, getURL( url ), request.toJSON() );
    }

    public ResponseWrapper patch( final AccessType accessType, final String url )
    {
        return httpRequest( RequestMethod.PATCH, accessType, getURL( url ), null );
    }

    public ResponseWrapper patch( final AccessType accessType, final String url, final Request request )
    {
        return httpRequest( RequestMethod.PATCH, accessType, getURL( url ), request.toJSON() );
    }

    private URL getURL( final String path )
    {
        try
        {
            return new URL( myConfiguration.getBaseURL() + path );
        }
        catch ( final MalformedURLException e )
        {
            throw new RuntimeException( e );
        }
    }

    private ResponseWrapper httpRequest( final RequestMethod requestMethod,
                                         final AccessType accessType,
                                         final URL url,
                                         final String postBody )
    {
        HttpURLConnection connection = null;
        final String contentType = "application/json";
        ResponseWrapper response = null;

        try
        {
            connection = buildConnection( requestMethod, accessType, url, contentType );
            final Logger logger = myConfiguration.getLogger();

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
                final String jsonResponse = StringUtil.inputStreamToString( inputStream );

                logger.log(
                        Level.INFO,
                        Configuration.theLogPrefix + " [{0}] {1} {2} {3} {4}",
                        new Object[] { getCurrentTime(), requestMethod.toString(), url, connection.getResponseCode(), postBody } );

                if( jsonResponse != null )
                {
                    logger.log( Level.INFO, sanitiseRequestBodyForLogging( jsonResponse ) );
                }

                if( StringUtils.isEmpty( jsonResponse ) )
                {
                    return null;
                }

                response = new ResponseWrapper( jsonResponse, connection.getResponseCode() );
            }
        }
        catch( final SocketTimeoutException e )
        {
            throw new TimeoutException( e.getMessage(), e );
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
            ValidationErrors errors = null;
            try( InputStream inputStream = connection.getErrorStream() )
            {
                errors = ValidationErrors.from( StringUtil.inputStreamToString( inputStream ) );
            }

            final Logger logger = myConfiguration.getLogger();

            logger.log(
                    Level.INFO,
                    Configuration.theLogPrefix + " [{0}]] {1} {2}",
                    new Object[] { getCurrentTime(), errors.getStatus(), errors.getRequestUrl() } );
            logger.log(
                    Level.FINE,
                    Configuration.theLogPrefix + " [{0}]] {1} {2} {3}",
                    new Object[] {
                            getCurrentTime(),
                            errors.getStatus(),
                            errors.getRequestMethod().name() + " " + errors.getRequestUrl(),
                            errors.getDeveloperMessage() } );

            switch( connection.getResponseCode() )
            {
                case 401 :
                    throw new AuthenticationException( errors );
                case 403 :
                    throw new AuthorizationException( errors );
                case 429 :
                    throw new TooManyRequestsException( errors );
                case 500 :
                    throw new ServerException( errors );
                case 503 :
                    throw new DownForMaintenanceException( errors );
                default:
                    throw new UnexpectedException(
                            "Unexpected HTTP Response " + connection.getResponseCode(), errors );
            }
        }
    }

    private HttpURLConnection buildConnection( final RequestMethod requestMethod,
                                               final AccessType accessType,
                                               final URL url,
                                               final String contentType ) throws IOException
    {
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

        if( requestMethod != RequestMethod.GET )
        {
            connection.setRequestProperty( "Content-Type", contentType );
        }

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
        return responseCode != 200 && responseCode != 201 && responseCode != 204 && responseCode != 422;
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

        regex = Pattern.compile( "(\\d{6})\\d+(\\d{4})" );
        matcher = regex.matcher( sanitisedRequestBody );
        if ( matcher.find() )
        {
            sanitisedRequestBody = matcher.replaceAll( "$1xxxxxx$2" );
        }

        return sanitisedRequestBody;
    }
}