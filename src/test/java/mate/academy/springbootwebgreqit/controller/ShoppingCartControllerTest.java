package mate.academy.springbootwebgreqit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mate.academy.springbootwebgreqit.dto.cartitem.CartItemRequestDto;
import mate.academy.springbootwebgreqit.model.Book;
import mate.academy.springbootwebgreqit.model.User;
import mate.academy.springbootwebgreqit.service.ShoppingCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.math.BigDecimal;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShoppingCartControllerTest {
    protected static MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ShoppingCartService shoppingCartService;

    private User user;
    private Book book;

    @BeforeEach
    void setUp() {
        // Створення користувача та книги для тестів
        user = new User();
        user.setId(1L);
        user.setFirstName("john");
        user.setLastName("doe");
        user.setPassword("password");

        book = new Book();
        book.setId(1L);
        book.setTitle("Sample Book");
        book.setAuthor("John Author");
        book.setIsbn("1234567890");
        book.setPrice(BigDecimal.valueOf(10.99));
        book.setDescription("A sample book description.");
        book.setCoverImage("https://example.com/book-cover.jpg");

        // Мокінг або створення залежностей в класі ShoppingCartService.
    }

    @Sql(scripts = "/data-sql/clear-tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @WithMockUser(username = "john_doe", roles = {"USER"})
    @Test
    @DisplayName("Get shopping cart for the current user")
    void getShoppingCart_ShouldReturnCartForUser() throws Exception {
        mockMvc.perform(get("/cart")
                        .param("userId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.items").isArray())
                .andReturn();
    }

    @Sql(scripts = {"/data-sql/clear-tables.sql", "/data-sql/insert-tables-for-shoppingcart.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @WithMockUser(username = "john_doe", roles = {"USER"})
    @Test
    @DisplayName("Add book to shopping cart")
    void addBookToShoppingCart_ShouldReturnUpdatedCart() throws Exception {
        CartItemRequestDto cartItemRequestDto = new CartItemRequestDto();
        cartItemRequestDto.setBookId(1L);  // Використовуємо ID книги, яка була додана в базу
        cartItemRequestDto.setQuantity(2);

        String jsonRequest = objectMapper.writeValueAsString(cartItemRequestDto);

        MvcResult result = mockMvc.perform(post("/cart")
                        .param("userId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items[0].book.title").value("wallet"))  // Перевіряємо, чи правильна книга
                .andExpect(jsonPath("$.items[0].quantity").value(2))  // Перевіряємо кількість
                .andReturn();
    }


    @Sql(scripts = "/data-sql/clear-tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @WithMockUser(username = "john_doe", roles = {"USER"})
    @Test
    @DisplayName("Update cart item quantity")
    void updateCartItemQuantity_ShouldReturnUpdatedQuantity() throws Exception {
        Long cartItemId = 1L;
        int newQuantity = 3;

        mockMvc.perform(put("/cart/items/{cartItemId}", cartItemId)
                        .param("quantity", String.valueOf(newQuantity))
                        .param("userId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items[0].quantity").value(newQuantity))
                .andReturn();
    }

    @Sql(scripts = "/data-sql/clear-tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @WithMockUser(username = "john_doe", roles = {"USER"})
    @Test
    @DisplayName("Remove item from cart")
    void removeItemFromCart_ShouldReturnEmptyCart() throws Exception {
        Long cartItemId = 1L;

        mockMvc.perform(delete("/cart/items/{cartItemId}", cartItemId)
                        .param("userId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items").isEmpty())
                .andReturn();
    }
}
