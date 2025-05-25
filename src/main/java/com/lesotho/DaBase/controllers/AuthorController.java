package com.lesotho.DaBase.controllers;


import com.lesotho.DaBase.domain.Author;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    @PostMapping(path = "/authors")
    public Author createAuthor

}
