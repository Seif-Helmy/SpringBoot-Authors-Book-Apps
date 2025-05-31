package com.lesotho.DaBase.services;

import com.lesotho.DaBase.domain.entities.BookEntity;

public interface BookService {

    BookEntity createBook(String isbn, BookEntity book);




}
