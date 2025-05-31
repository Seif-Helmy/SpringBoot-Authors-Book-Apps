package com.lesotho.DaBase.services;

import com.lesotho.DaBase.domain.entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorsService {

    AuthorEntity createAuthor(AuthorEntity author);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findOne(Long id);
}
