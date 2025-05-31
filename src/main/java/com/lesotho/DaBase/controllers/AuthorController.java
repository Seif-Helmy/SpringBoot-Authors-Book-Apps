package com.lesotho.DaBase.controllers;


import com.lesotho.DaBase.domain.dto.AuthorDto;
import com.lesotho.DaBase.domain.entities.AuthorEntity;
import com.lesotho.DaBase.mappers.Mapper;
import com.lesotho.DaBase.services.AuthorsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


}
