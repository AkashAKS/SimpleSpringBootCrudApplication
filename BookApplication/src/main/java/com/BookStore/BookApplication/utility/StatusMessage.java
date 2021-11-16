package com.BookStore.BookApplication.utility;

public class StatusMessage {
    private String title;
    private String details;

    public StatusMessage()
    {
        super();
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title=title;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
