package com.qvalent.quickstreamapi.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
}
