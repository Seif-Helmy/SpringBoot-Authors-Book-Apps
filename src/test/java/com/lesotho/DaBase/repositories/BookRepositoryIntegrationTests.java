package com.lesotho.DaBase.repositories;

import com.lesotho.DaBase.TestDataUtil;
import com.lesotho.DaBase.domain.entities.AuthorEntity;
import com.lesotho.DaBase.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTests {
    private BookRepository bookUnderTest;

    @Autowired
    public BookRepositoryIntegrationTests(BookRepository bookUnderTest) {
        this.bookUnderTest = bookUnderTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        AuthorEntity author = TestDataUtil.createTestAuthor1();
        BookEntity book = TestDataUtil.createBook1(author);

        bookUnderTest.save(book);

        Optional<BookEntity> result = bookUnderTest.findById(book.getIsbn());
        assertThat(result).isPresent();
    }


    @Test
    public void testThatMultipleBooksCanBeCreatedRecalled() {
        AuthorEntity author = TestDataUtil.createTestAuthor1();

        BookEntity book1 = TestDataUtil.createBook1(author);
        BookEntity book2 = TestDataUtil.createBook2(author);
        BookEntity book3 = TestDataUtil.createBook3(author);

        bookUnderTest.save(book1);
        bookUnderTest.save(book2);
        bookUnderTest.save(book3);

        Iterable<BookEntity> result = bookUnderTest.findAll();
        assertThat(result).hasSize(3);
    }


    @Test
    public void testTHatBookCanBeUpdated() {
        AuthorEntity author = TestDataUtil.createTestAuthor1();
        BookEntity book1 = TestDataUtil.createBook1(author);
        bookUnderTest.save(book1);

        book1.setTitle("iouvhvweruvhierhvierhv");

        bookUnderTest.save(book1);

        Optional<BookEntity> result = bookUnderTest.findById(book1.getIsbn());
        assertThat(result).isPresent();
    }


    @Test
    public void testThatBookCanBeDeleted() {
        AuthorEntity author = TestDataUtil.createTestAuthor1();
        BookEntity book1 = TestDataUtil.createBook1(author);
        bookUnderTest.save(book1);

        bookUnderTest.deleteById(book1.getIsbn());
        Optional<BookEntity> result = bookUnderTest.findById(book1.getIsbn());
        assertThat(result).isEmpty();
    }




}


