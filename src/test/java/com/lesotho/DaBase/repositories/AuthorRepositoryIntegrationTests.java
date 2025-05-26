package com.lesotho.DaBase.repositories;


import com.lesotho.DaBase.TestDataUtil;
import com.lesotho.DaBase.domain.entities.AuthorEntity;
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
public class AuthorRepositoryIntegrationTests {

    private final AuthorRepository authorUnderTest;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository authorUnderTest) {
        this.authorUnderTest = authorUnderTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        AuthorEntity author = TestDataUtil.createTestAuthor1();
        authorUnderTest.save(author);

        Optional<AuthorEntity> resultAuthor = authorUnderTest.findById(author.getId());
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndQueried() {
        AuthorEntity author1 = TestDataUtil.createTestAuthor1();
        AuthorEntity author2 = TestDataUtil.createTestAuthor2();
        AuthorEntity author3 = TestDataUtil.createTestAuthor3();

        authorUnderTest.save(author2);
        authorUnderTest.save(author3);
        authorUnderTest.save(author1);

        Iterable<AuthorEntity> result = authorUnderTest.findAll();

        assertThat(result).hasSize(3);

    }


    @Test
    public void testThatAuthorCanBeUpdated() {
        AuthorEntity author1 = TestDataUtil.createTestAuthor1();
        authorUnderTest.save(author1);

        author1.setName("UPDATED");
        authorUnderTest.save(author1);

        Optional<AuthorEntity> result = authorUnderTest.findById(author1.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author1);
    }


    @Test
    public void testTHatAuthorCanBeDeleted() {
        AuthorEntity author1 = TestDataUtil.createTestAuthor1();
        authorUnderTest.save(author1);

        authorUnderTest.deleteById(author1.getId());
        Optional<AuthorEntity> result = authorUnderTest.findById(author1.getId());
        assertThat(result).isEmpty();

    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan() {
        AuthorEntity author1 = TestDataUtil.createTestAuthor1();
        AuthorEntity author2 = TestDataUtil.createTestAuthor2();
        AuthorEntity author3 = TestDataUtil.createTestAuthor3();

        authorUnderTest.save(author1);
        authorUnderTest.save(author2);
        authorUnderTest.save(author3);

        Iterable<AuthorEntity> result = authorUnderTest.ageLessThan(20);
        assertThat(result).hasSize(2).containsExactly(author2, author3);

    }

    @Test
    public void testThatGetsAuthorsWithAgeGreaterThan() {
        AuthorEntity author1 = TestDataUtil.createTestAuthor1();
        AuthorEntity author2 = TestDataUtil.createTestAuthor2();
        AuthorEntity author3 = TestDataUtil.createTestAuthor3();

        authorUnderTest.save(author1);
        authorUnderTest.save(author2);
        authorUnderTest.save(author3);

        Iterable<AuthorEntity> result = authorUnderTest.findAuthorsWithAgeGreaterThan(20);
        assertThat(result).hasSize(1).containsExactly(author1);
    }



}
