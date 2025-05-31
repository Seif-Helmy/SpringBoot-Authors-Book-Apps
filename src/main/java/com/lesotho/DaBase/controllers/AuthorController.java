package com.lesotho.DaBase.controllers;


import com.lesotho.DaBase.domain.dto.AuthorDto;
import com.lesotho.DaBase.domain.dto.BookDto;
import com.lesotho.DaBase.domain.entities.AuthorEntity;
import com.lesotho.DaBase.domain.entities.BookEntity;
import com.lesotho.DaBase.mappers.Mapper;
import com.lesotho.DaBase.services.AuthorsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

    private final AuthorsService authorsService;

    private final Mapper<AuthorEntity, AuthorDto> authorMapper;


    public AuthorController(AuthorsService authorsService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
        this.authorsService = authorsService;
        this.authorMapper = authorMapper;
    }


    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity author = authorsService.createAuthor(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(author), HttpStatus.CREATED);
    }

    @GetMapping(path = "/authors")
    public List<AuthorDto> listAuthors() {
        List<AuthorEntity> authors = authorsService.findAll();
        return authors.stream().map(authorMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable("id") Long id) {
        Optional<AuthorEntity> authorEntity = authorsService.findOne(id);

        return authorEntity.map(author -> {
            AuthorDto responseAuthor = authorMapper.mapTo(author);
            return new ResponseEntity<>(responseAuthor, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


}
