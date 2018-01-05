package com.qvalent.quickstreamapi.model.response;

import java.util.List;

public class ValidationError
{
    private final String fieldName;
    private final String fieldValue;
    private final List<String> messages;

    public ValidationError()
    {
        fieldName = null;
        fieldValue = null;
        messages = null;
    }

    public String getFieldName()
    {
        return fieldName;
    }

    public String getFieldValue()
    {
        return fieldValue;
    }

    public List<String> getMessages()
    {
        return messages;
    }

    @Override
    public String toString()
    {
        return "ValidationError [fieldName=" + fieldName
                + ", fieldValue=" + fieldValue
                + ", messages=" + messages + "]";
    }
}
