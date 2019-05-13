package com.qvalent.quickstreamapi.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class StringUtil
{
    public static String inputStreamToString( final InputStream inputStream ) throws IOException
    {
        final InputStreamReader inputReader = new InputStreamReader(inputStream);
        final StringBuilder builder = new StringBuilder();
        final char[] buffer = new char[0x1000];
        int bytesRead = inputReader.read(buffer, 0, buffer.length);
        while (bytesRead >= 0) {
            builder.append(buffer, 0, bytesRead);
            bytesRead = inputReader.read(buffer, 0, buffer.length);
        }
        return builder.toString();
    }

    public static String getQueryString( final Map<String, String> parameterMap )
    {
        final List<BasicNameValuePair> parameters = new ArrayList<>();
        for( final Map.Entry<String, String> entry : parameterMap.entrySet() )
        {
            if( StringUtils.isNotEmpty( entry.getValue() ) )
            {
                parameters.add( new BasicNameValuePair( entry.getKey(), entry.getValue()) );
            }
        }
        return "?" + URLEncodedUtils.format( parameters, "UTF-8" );
    }
}
