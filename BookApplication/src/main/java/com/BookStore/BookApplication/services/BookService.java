package com.BookStore.BookApplication.services;

import com.BookStore.BookApplication.entity.Book;
import com.BookStore.BookApplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookDAO;

    public List<Book> getBooks() {
        return bookDAO.findAll();
    }


    public Book createBook(Book book) {
        return bookDAO.save(book);
    }


    public Optional<Book> updateBook(int bookId, Book book) {

        Optional<Book> bookAvailable = bookDAO.findById(bookId);
        if(bookAvailable.isPresent()) {
            Book bookToBeUpdate = bookAvailable.get();
            bookToBeUpdate.setName(book.getName());
            bookToBeUpdate.setAuthor(book.getAuthor());
            bookToBeUpdate.setCategory(book.getCategory());
            bookToBeUpdate.setPublication(book.getPublication());
            bookToBeUpdate.setPages(book.getPages());
            bookToBeUpdate.setPrice(book.getPrice());
            bookDAO.save(bookToBeUpdate);
        }
        Optional<Book> updatedBook = getBook(bookId);
        return updatedBook;
    }


    public Optional<Book> getBook(int bookId) {

        Optional<Book> optionalBook = bookDAO.findById(bookId);
        if(!optionalBook.isPresent())
        {
            System.out.println("Book Record is not available..");
        }
        //return optionalBook.get();

        return bookDAO.findById(bookId);
    }


    public boolean deleteBook(int bookId) {
        if(bookDAO.existsById(bookId))
        {
            bookDAO.deleteById(bookId);
            return true;
        }
        return false;
    }
}
