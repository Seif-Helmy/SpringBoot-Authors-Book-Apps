package com.lesotho.DaBase.services;

import com.lesotho.DaBase.domain.entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorsService {

    AuthorEntity save(AuthorEntity author);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findOne(Long id);

    boolean Exists(Long id);

    AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity);

    void delete(Long id);
}
