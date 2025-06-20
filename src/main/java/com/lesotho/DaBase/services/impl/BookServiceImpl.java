package com.lesotho.DaBase.services.impl;

import com.lesotho.DaBase.domain.entities.BookEntity;
import com.lesotho.DaBase.repositories.BookRepository;
import com.lesotho.DaBase.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public BookEntity save(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }

    @Override
    public List<BookEntity> findAll() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<BookEntity> findOne(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public Boolean Exists(String isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public BookEntity partialUpdate(String isbn, BookEntity bookEntity) {

        bookEntity.setIsbn(isbn);

        return bookRepository.findById(isbn).map(book -> {

            if (bookEntity.getTitle() != null) {
                book.setTitle(bookEntity.getTitle());
            }

            return bookRepository.save(book);

        }).orElseThrow();

    }

    @Override
    public void delete(String isbn) {
        bookRepository.deleteById(isbn);
    }

}
