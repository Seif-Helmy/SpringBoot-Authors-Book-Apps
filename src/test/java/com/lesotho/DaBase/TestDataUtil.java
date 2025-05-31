package com.lesotho.DaBase;

import com.lesotho.DaBase.domain.dto.AuthorDto;
import com.lesotho.DaBase.domain.dto.BookDto;
import com.lesotho.DaBase.domain.entities.AuthorEntity;
import com.lesotho.DaBase.domain.entities.BookEntity;


public final class TestDataUtil {
    public static AuthorEntity createTestAuthor1() {
        return AuthorEntity.builder().name("Motombo").age(30).build();
    }

    public static AuthorEntity createTestAuthor2() {
        return AuthorEntity.builder().name("Uvevevwe").age(12).build();
    }

    public static AuthorEntity createTestAuthor3() {
        return AuthorEntity.builder().name("Adimola").age(19).build();
    }

    public static AuthorDto createTestAuthorDto1() {
        return AuthorDto.builder().name("Motombo").age(30).build();
    }


    public static BookEntity createBook1(final AuthorEntity author) {
        return BookEntity.builder().isbn("0t843gh793g95g").title("Africa").author(author).build();
    }

    public static BookEntity createBook2(final AuthorEntity author) {
        return BookEntity.builder().isbn("0t843gh793g95h").title("Zimbabwe").author(author).build();
    }

    public static BookEntity createBook3(final AuthorEntity author) {
        return BookEntity.builder().isbn("0t843gh793g95j").title("Congo").author(author).build();
    }

    public static BookDto createBookDto1(final AuthorDto author) {
        return BookDto.builder().isbn("0t843gh793g95g").title("Africa").author(author).build();
    }

}
