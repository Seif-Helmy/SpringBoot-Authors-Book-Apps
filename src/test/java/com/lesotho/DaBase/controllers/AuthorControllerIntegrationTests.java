package com.lesotho.DaBase.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lesotho.DaBase.TestDataUtil;
import com.lesotho.DaBase.domain.dto.AuthorDto;
import com.lesotho.DaBase.domain.entities.AuthorEntity;
import com.lesotho.DaBase.services.AuthorsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTests {

    private AuthorsService authorsService;
    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @Autowired
    public AuthorControllerIntegrationTests(MockMvc mockMvc, AuthorsService authorsService) {
        this.mockMvc = mockMvc;
        this.authorsService = authorsService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsHttp201Created() throws Exception {
        AuthorEntity author1 = TestDataUtil.createTestAuthor1();
        author1.setId(null);

        String author1JSON = objectMapper.writeValueAsString(author1);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(author1JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );

    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
        AuthorEntity author1 = TestDataUtil.createTestAuthor1();
        author1.setId(null);

        String author1JSON = objectMapper.writeValueAsString(author1);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(author1JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Motombo")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value("30")
        );

    }

    @Test
    public void thatThatListAuthorReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void thatThatListAuthorReturnsListOfAuthors() throws Exception {
        AuthorEntity author1 = TestDataUtil.createTestAuthor1();
        authorsService.save(author1);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.[0].id").isNumber()
        );
    }

    @Test
    public void testThatGetAuthorReturnHttpStatus200WhenAuthorExists() throws Exception {
        AuthorEntity author1 = TestDataUtil.createTestAuthor1();
        authorsService.save(author1);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAuthorReturnHttpStatus404WhenAuthorNotExists() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetAuthorReturnsAuthorWhenAuthorExists() throws Exception {
        AuthorEntity author1 = TestDataUtil.createTestAuthor1();
        String author1JSON = objectMapper.writeValueAsString(author1);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(author1JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Motombo")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value("30")
        );

    }


    @Test
    public void testThatFullUpdateReturns404WhenNoAuthorExists() throws Exception {
        AuthorEntity author1 = TestDataUtil.createTestAuthor1();
        authorsService.save(author1);

        AuthorDto authorDto1 = TestDataUtil.createTestAuthorDto1();
        String authorDto1JSON = objectMapper.writeValueAsString(authorDto1);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDto1JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatFullUpdateReturns200WheAuthorExists() throws Exception {
        AuthorEntity author1 = TestDataUtil.createTestAuthor1();
        AuthorEntity savedAuthor =  authorsService.save(author1);

        AuthorDto authorDto1 = TestDataUtil.createTestAuthorDto1();
        authorDto1.setName("gioewrngiogioegioergboierwbgioewrbgibewrgberwngowe");
        String authorDto1JSON = objectMapper.writeValueAsString(authorDto1);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDto1JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatFullUpdateUpdatesAuthor() throws Exception {
        AuthorEntity author1 = TestDataUtil.createTestAuthor1();
        AuthorEntity savedAuthor =  authorsService.save(author1);

        AuthorEntity author2 = TestDataUtil.createTestAuthor2();
        author2.setId(savedAuthor.getId());
        String author2JSON = objectMapper.writeValueAsString(author2);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(author2JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(author2.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(author2.getAge())
        );
    }

    @Test
    public void testThatPartialUpdateExistingAuthorReturnsHttpStatus200() throws Exception{
        AuthorEntity author1 = TestDataUtil.createTestAuthor1();
        AuthorEntity savedAuthor =  authorsService.save(author1);

        AuthorEntity author2 = TestDataUtil.createTestAuthor2();
        author2.setName("UPDATED");
        String author2JSON = objectMapper.writeValueAsString(author2);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/authors/" + savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(author2JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatPartialUpdateExistingAuthor() throws Exception {
        AuthorEntity author1 = TestDataUtil.createTestAuthor1();
        AuthorEntity savedAuthor =  authorsService.save(author1);

        AuthorEntity author2 = TestDataUtil.createTestAuthor2();
        author2.setId(author1.getId());
        author2.setName("UPDATED");
        String author2JSON = objectMapper.writeValueAsString(author2);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/authors/" + savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(author2JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("UPDATED")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(author2.getAge())
        );
    }


    @Test
    public void testThatDeleteAuthorReturnHttp204() throws Exception {
        AuthorEntity author1 = TestDataUtil.createTestAuthor1();
        AuthorEntity savedAuthor =  authorsService.save(author1);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/authors/" + savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }



}
