package com.BookStore.BookApplication.utility;

import org.springframework.stereotype.Service;

@Service
public class MessageGenerator {
    public MessageGenerator() {
        super();
    }

    public Status buildStatusMessage(int statusCode, String title)
    {
        StatusMessage message = new StatusMessage();
        message.setTitle(title);
        return new Status(statusCode,message);
    }

    public Status buildStatusMessage(int statusCode, String title,String details)
    {
        StatusMessage message = new StatusMessage();
        message.setTitle(title);
        message.setDetails(details);
        return new Status(statusCode,message);
    }

}
