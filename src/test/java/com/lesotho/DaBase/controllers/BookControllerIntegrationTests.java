package com.lesotho.DaBase.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lesotho.DaBase.TestDataUtil;
import com.lesotho.DaBase.domain.entities.BookEntity;
import com.lesotho.DaBase.services.BookService;
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
public class BookControllerIntegrationTests {

    private final MockMvc mockMvc;

     private final ObjectMapper objectMapper;

     private final BookService bookService;


    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, BookService bookService) {
        this.mockMvc = mockMvc;
        this.bookService = bookService;
        this.objectMapper  = new ObjectMapper();
    }

    @Test
    public void testThatCreateBooksReturnHttp201() throws Exception {

      BookEntity bookDto = TestDataUtil.createBook1(null);
//        BookDto bookDto = TestDataUtil.createBookDto1(null);

        String createBookJSON = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJSON)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );

    }

    @Test
    public void testThatCreateBookReturnsSavedBook() throws Exception {
        BookEntity bookEntity = TestDataUtil.createBook1(null);
        String bookJSON = objectMapper.writeValueAsString(bookEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(bookEntity.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookEntity.getTitle())
        );

    }

    @Test
    public void testThatListsBooksReturnHttp200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListsBooksReturnBooks() throws Exception {
        BookEntity bookEntity1 = TestDataUtil.createBook1(null);
        bookService.createBook(bookEntity1.getIsbn(), bookEntity1);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isbn").value(bookEntity1.getIsbn())
        );
    }

    @Test
    public void testThatGetBookReturnHttpsStatus200WhenBookExists() throws Exception {
        BookEntity bookEntity1 = TestDataUtil.createBook1(null);
        bookService.createBook(bookEntity1.getIsbn(), bookEntity1);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/" + bookEntity1.getIsbn() )
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }

    @Test
    public void testThatGetBookReturnHttpsStatus400WhenBookNotExists() throws Exception {
        BookEntity bookEntity1 = TestDataUtil.createBook1(null);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/" + bookEntity1.getIsbn() )
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );

    }



}
