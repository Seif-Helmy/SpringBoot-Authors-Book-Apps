package com.lesotho.DaBase.services.impl;

import com.lesotho.DaBase.domain.entities.BookEntity;
import com.lesotho.DaBase.repositories.BookRepository;
import com.lesotho.DaBase.services.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public BookEntity createBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }
}
