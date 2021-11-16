package com.BookStore.BookApplication.utility;

public class Status {
    private int statusCode;
    private StatusMessage statusMessage;

    public Status() {
        super();
    }

    public Status(int statusCode,StatusMessage statusMessage)
    {
        this.statusCode=statusCode;
        this.statusMessage = statusMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public StatusMessage getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(StatusMessage statusMessage) {
        this.statusMessage = statusMessage;
    }
}
