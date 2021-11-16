package com.BookStore.BookApplication.controller;

import com.BookStore.BookApplication.entity.Book;
import com.BookStore.BookApplication.services.BookService;
import com.BookStore.BookApplication.utility.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("bookservice")
public class BookController {

    private static final String SERVER_ERROR_MSG = "{\"status\":{\"code\":500,\"message\":{\"title\":\"Internal server error.\","+
            "\"details\":\"Internal server error.\"}}}";

    private final ObjectMapper objMapper;

    @Autowired
    private MessageGenerator messageGenerator;

    public BookController()
    {
        this.objMapper= new ObjectMapper();
    }

    @Autowired
    private BookService service;



    @GetMapping("books")
    public Optional<BookResponse> getBooks() {
        try
        {
            BookResponse response = new BookResponse();
            List<Book> bookList = service.getBooks();
            response.setBooks(bookList);
            response.setStatus(messageGenerator.buildStatusMessage(ResponseCode.STATUS_OK, ResponseMessage.RESOURCE_OK));
            return Optional.of(response);

        }
        catch(Exception e)
        {
//            return this.generateServerErrorMessage();
            e.printStackTrace();
            return Optional.empty();
        }
//        return Optional.of("Bye");
        //return new ResponseEntity<List<Book>>(books, HttpStatus.OK);


    }

    @GetMapping("books/{id}")
    public Optional<BookResponse> getBook(@PathVariable("id") Integer id) {
        try {
            if (id == null)
            {
                return this.generateBadRequestMessage(ResponseMessage.BAD_REQ_DESC_NULL_REQ);
            }

            BookResponse response = new BookResponse();
            Optional<Book> book = service.getBook(id);
            if (book.isPresent()) {
                List<Book> bookList = new ArrayList<>();
                bookList.add(book.get());
                response.setBooks(bookList);
                response.setStatus(messageGenerator.buildStatusMessage(ResponseCode.STATUS_OK, ResponseMessage.RESOURCE_OK));
            } else {
                response.setStatus(messageGenerator.buildStatusMessage(ResponseCode.NOT_FOUND, ResponseMessage.RESOURCE_NOT_FOUND));
            }
            return Optional.of(response);
        }
        catch(Exception e)
        {
            return this.generateServerErrorMessage();
        }
    }

    @PostMapping("books")
    public Optional<BookResponse> createBook(@RequestBody Book book) {
        try {
            if (book == null) {
                return this.generateBadRequestMessage(ResponseMessage.BAD_REQ_DESC_NULL_REQ);
            }

            Book createdBook = service.createBook(book);

            BookResponse response = new BookResponse();
            List<Book> bookList = new ArrayList<>();
            bookList.add(createdBook);
            response.setBooks(bookList);
            response.setStatus(messageGenerator.buildStatusMessage(ResponseCode.STATUS_CREATED, "Book entry created"));
            return Optional.of(response);
            //return new ResponseEntity<Book>(b, HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return this.generateServerErrorMessage();
        }
    }

    @PutMapping("books/{id}")
    public Optional<BookResponse> updateBook(@PathVariable("id") Integer id, @RequestBody Book book) {
        try {
            if (id == null || book == null) {
                return this.generateBadRequestMessage(ResponseMessage.BAD_REQ_DESC_NULL_REQ);
            }

            BookResponse response = new BookResponse();
            Optional<Book> updatedBook = service.updateBook(id, book);
            List<Book> bookList = new ArrayList<>();
            if(updatedBook.isPresent())
            {
                bookList.add(updatedBook.get());
                response.setBooks(bookList);
                response.setStatus(messageGenerator.buildStatusMessage(ResponseCode.STATUS_ACCEPTED, "Book entry updated"));
                return Optional.of(response);
            }
            else
            {
                response.setStatus(messageGenerator.buildStatusMessage(ResponseCode.STATUS_ACCEPTED, "Book entry not found in Database for updating"));
                return Optional.of(response);
            }
        }
        catch (Exception e)
        {
            return this.generateServerErrorMessage();
        }

        //return new ResponseEntity<Book>(b, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("books/{id}")
    public Optional<BookResponse> deleteBook(@PathVariable("id") Integer id) {
        try {
            if (id == null ) {
                return this.generateBadRequestMessage(ResponseMessage.BAD_REQ_DESC_NULL_REQ);
            }
            boolean isDeleted = service.deleteBook(id);
            BookResponse response = new BookResponse();
            if (isDeleted) {
                response.setStatus(messageGenerator.buildStatusMessage(ResponseCode.STATUS_ACCEPTED, "Book has been deleted successfully"));
                return Optional.of(response);
            }
            else
            {
                response.setStatus(messageGenerator.buildStatusMessage(ResponseCode.STATUS_ACCEPTED, "Book entry not found in database"));
                return Optional.of(response);

            }
        }
        catch (Exception e)
        {
            return this.generateServerErrorMessage();
        }

    }

    public Optional<BookResponse> generateBadRequestMessage(String description)
    {
        try
        {
            BookResponse response = new BookResponse();
            Status status= messageGenerator.buildStatusMessage(ResponseCode.BAD_REQUEST, ResponseMessage.BAD_REQ_TITLE,ResponseMessage.BAD_REQ_DESC_NULL_REQ);
            response.setStatus(status);
            return Optional.of(response);
        }
        catch(Exception e)
        {
            //return Optional.of(SERVER_ERROR_MSG);
            return Optional.empty();
        }
    }

    public Optional<BookResponse> generateServerErrorMessage()
    {
        try
        {
            BookResponse response = new BookResponse();
            Status status= messageGenerator.buildStatusMessage(ResponseCode.INTERNAL_SERVER_ERROR, ResponseMessage.SERVER_ERROR_TITLE,ResponseMessage.SERVER_ERROR_DESC);
            response.setStatus(status);
            return Optional.of(response);
        }
        catch(Exception e)
        {
            //return Optional.of(SERVER_ERROR_MSG);
            return Optional.empty();
        }
    }
}
