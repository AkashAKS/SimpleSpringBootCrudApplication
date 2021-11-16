package com.BookStore.BookApplication.utility;

public class ResponseCode {

    public ResponseCode()
    {
        super();
    }

    //Status code from series 200+
    public static final int STATUS_OK = 200;
    public static final int STATUS_CREATED = 201;
    public static final int STATUS_ACCEPTED = 202;
    public static final int STATUS_NO_CONTENT = 204;

    //Status code from series 400+
    public static final int BAD_REQUEST = 400;
    public static final int NOT_FOUND = 404;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;

    //Status code from series 500+
    public static final int INTERNAL_SERVER_ERROR = 500;

}
