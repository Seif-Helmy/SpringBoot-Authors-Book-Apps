package com.lesotho.DaBase.services.impl;

import com.lesotho.DaBase.domain.entities.AuthorEntity;
import com.lesotho.DaBase.repositories.AuthorRepository;
import com.lesotho.DaBase.services.AuthorsService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorsService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity author) {
        return authorRepository.save(author);
    }
}
