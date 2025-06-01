package com.lesotho.DaBase.services.impl;

import com.lesotho.DaBase.domain.entities.AuthorEntity;
import com.lesotho.DaBase.repositories.AuthorRepository;
import com.lesotho.DaBase.services.AuthorsService;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorsService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity save(AuthorEntity author) {
        return authorRepository.save(author);
    }

    @Override
    public List<AuthorEntity> findAll() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorEntity> findOne(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public boolean Exists(Long id) {
        return authorRepository.existsById(id);
    }

    @Override
    public AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity) {
        authorEntity.setId(id);

        return authorRepository.findById(id).map(existingAuthor -> {

            if (authorEntity.getName() != null) {
                existingAuthor.setName(authorEntity.getName());
            }

            if (authorEntity.getAge() != null) {
                existingAuthor.setAge(authorEntity.getAge());
            }

            return authorRepository.save(existingAuthor);
                }).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
