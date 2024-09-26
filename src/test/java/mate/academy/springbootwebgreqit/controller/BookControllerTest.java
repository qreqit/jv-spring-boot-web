package mate.academy.springbootwebgreqit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mate.academy.springbootwebgreqit.dto.BookDto;
import mate.academy.springbootwebgreqit.dto.CreateBookRequestDto;
import mate.academy.springbootwebgreqit.dto.UpdateBookRequestDto;
import mate.academy.springbootwebgreqit.model.Category;
import mate.academy.springbootwebgreqit.service.BookService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {
    protected static MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BookService bookService;

    private BookDto bookDto;
    private UpdateBookRequestDto updateRequestDto;
    private Category category;

    @BeforeAll
    static void beforeAll(@Autowired WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Roman");

        bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("wallet");
        bookDto.setAuthor("John");
        bookDto.setIsbn("9283234577892");
        bookDto.setPrice(BigDecimal.valueOf(60.99));
        bookDto.setDescription("Updated description");
        bookDto.setCoverImage("https://example.com/updated-cover-.jpg");
        bookDto.setCategoriesIds(List.of(1L));

        updateRequestDto = new UpdateBookRequestDto();
        updateRequestDto.setId(bookDto.getId());
        updateRequestDto.setTitle("updated title");
        updateRequestDto.setAuthor("updated author");
        updateRequestDto.setIsbn("updated isbn");
        updateRequestDto.setPrice(BigDecimal.valueOf(70.99));
        updateRequestDto.setDescription("Updated description");
        updateRequestDto.setCoverImage("https://example.com/updated-cover.jpg");

    }

    @Test
    @DisplayName("Given available books in the database, retrieve the next available books")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAllBooks_ReturnsBooksFromDatabase() {
    }

    @Test
    @DisplayName("Get available book by id")
    void getBookById_ShouldReturnBookWithCurrentId() {
    }

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    @DisplayName("Create a book")
    @Sql(scripts = "/data-sql/create-category.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data-sql/clear-tables.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void createBook_ShouldReturnCreatedBook() throws Exception {
        CreateBookRequestDto requestDto = new CreateBookRequestDto();
        requestDto.setTitle("wallet");
        requestDto.setAuthor("John");
        requestDto.setIsbn("9283234577892");
        requestDto.setPrice(BigDecimal.valueOf(60.99));
        requestDto.setDescription("Updated description");
        requestDto.setCoverImage("https://example.com/updated-cover-.jpg");
        requestDto.setCategoriesIds(Collections.singletonList(1L));

        String jsonRequest = objectMapper.writeValueAsString(requestDto);
        System.out.println(jsonRequest);

        MvcResult result = mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("wallet"))
                .andExpect(jsonPath("$.author").value("John"))
                .andReturn();
    }

    @Test
    @DisplayName("Update a book")
    void updateBook_ShouldReturnUpdatedBook() {
        // Test implementation
    }

    @Test
    @DisplayName("Delete a book")
    void deleteBook_ShouldReturnNoContent() {
        // Test implementation
    }

    @Test
    @DisplayName("Search books with valid parameters")
    void searchBooks_ShouldReturnBooks() {
        // Test implementation
    }
}