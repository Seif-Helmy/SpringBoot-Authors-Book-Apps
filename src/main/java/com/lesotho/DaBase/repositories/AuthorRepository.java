package com.lesotho.DaBase.repositories;

import com.lesotho.DaBase.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {


    Iterable<AuthorEntity> ageLessThan(int age);

//    @Query("SELECT a FROM Author a WHERE a.age > ?1")
    @Query(value = "SELECT * FROM authors WHERE age > ?1", nativeQuery = true)
    Iterable<AuthorEntity> findAuthorsWithAgeGreaterThan(int i);
}
