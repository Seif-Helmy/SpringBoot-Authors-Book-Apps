package com.lesotho.DaBase.controllers;

import com.lesotho.DaBase.domain.dto.BookDto;
import com.lesotho.DaBase.domain.entities.BookEntity;
import com.lesotho.DaBase.mappers.Mapper;
import com.lesotho.DaBase.mappers.impl.BookMapper;
import com.lesotho.DaBase.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private final Mapper<BookEntity, BookDto> bookMapper;

    private final BookService bookService;

    public BookController(Mapper<BookEntity, BookDto> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;

    }


    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable("isbn") String isbn,
                                              @RequestBody BookDto bookDto) {
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity book = bookService.createBook(isbn, bookEntity);
        BookDto savedBookDto = bookMapper.mapTo(book);
        return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
    }

    @GetMapping(path = "/books")
    public List<BookDto> listBooks() {
        List<BookEntity> books = bookService.findAll();
        return books.stream().map(bookMapper::mapTo).collect(Collectors.toList());

    }


    @GetMapping(path = "books/{isbn}")
    public ResponseEntity<BookDto> getBook (@PathVariable("isbn") String isbn) {
        Optional<BookEntity> foundBook = bookService.findOne(isbn);
        return foundBook.map(book -> {
            BookDto resultBook = bookMapper.mapTo(book);
            return new ResponseEntity<>(resultBook, HttpStatus.OK);
        }).orElse( new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



}
