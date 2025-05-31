package com.lesotho.DaBase.controllers;

import com.lesotho.DaBase.domain.dto.BookDto;
import com.lesotho.DaBase.domain.entities.BookEntity;
import com.lesotho.DaBase.mappers.Mapper;
import com.lesotho.DaBase.mappers.impl.BookMapper;
import com.lesotho.DaBase.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
