package com.BookStore.BookApplication.utility;

import com.BookStore.BookApplication.entity.Book;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse {
    private Status status;
    private List<Book> books;

    public BookResponse()
    {
        super();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
