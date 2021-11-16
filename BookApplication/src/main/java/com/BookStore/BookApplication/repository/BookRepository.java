package com.BookStore.BookApplication.repository;

import com.BookStore.BookApplication.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

    @Override
    List<Book> findAll();
}
