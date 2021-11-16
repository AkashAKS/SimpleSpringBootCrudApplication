package com.BookStore.BookApplication.utility;

public class ResponseMessage {

    public ResponseMessage()
    {
        super();
    }

    public static final String BAD_REQ_TITLE = "Bad Request Sent by client.";
    public static final String BAD_REQ_DESC_NULL_REQ = "client sent NULL value as request message.";
    public static final String SERVER_ERROR_TITLE = "Internal Server error.";
    public static final String SERVER_ERROR_DESC = "Internal server error occured.";
    public static final String RESOURCE_CREATED = "Requested resource has been created.";
    public static final String RESOURCE_OK = "Request is successful.";
    public static final String RESOURCE_NOT_FOUND = "Requested resource not found.";
    public static final String RESOURCE_ACCEPTED = "Request has been accepted.";

}
