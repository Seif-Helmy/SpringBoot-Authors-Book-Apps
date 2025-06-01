package com.lesotho.DaBase.services;

import com.lesotho.DaBase.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookEntity save(String isbn, BookEntity book);


    List<BookEntity> findAll();

    Optional<BookEntity> findOne(String isbn);

    Boolean Exists(String isbn);

    BookEntity partialUpdate(String isbn, BookEntity bookEntity);

    void delete(String isbn);
}
