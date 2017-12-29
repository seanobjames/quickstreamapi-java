package com.qvalent.quickstreamapi.util;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;

import com.qvalent.quickstreamapi.Configuration;

public class Http
{
    public enum RequestMethod
    {
        GET, DELETE, POST, PUT, PATCH;
    }

    private Configuration myConfiguration;

    public Http( final Configuration configuration )
    {
        myConfiguration = configuration;
    }

    private void httpRequest( final RequestMethod requestMethod, final String url, final String postBody )
    {
        HttpURLConnection connection = null;
        String contentType = "application/json";

        try
        {
            connection = buildConnection();
            Logger logger = myConfiguration.getLogger();
            if ( postBody != null )
            {
                logger.log( Level.FINE, sanitiseRequestBodyForLogging( postBody ) );

                OutputStream outputStream = null;
                try
                {
                    outputStream = connection.getOutputStream();
                    final PrintWriter writer = new PrintWriter( new OutputStreamWriter( outputStream, "UTF-8" ), true );

                    JSONObject obj = new JSONObject( postBody );
                } finally
                {
                    if ( outputStream != null )
                    {
                        outputStream.close();
                    }
                }
            }

            // TODO handle HTTPS. Do we need a certificate?

        } finally
        {

        }
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
            requestBody = matcher.replaceAll( "[QuickStreamAPI] $1" );
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