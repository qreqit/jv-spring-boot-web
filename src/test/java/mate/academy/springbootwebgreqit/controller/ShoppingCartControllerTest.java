package mate.academy.springbootwebgreqit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mate.academy.springbootwebgreqit.dto.cartitem.CartItemRequestDto;
import mate.academy.springbootwebgreqit.model.Book;
import mate.academy.springbootwebgreqit.model.CartItem;
import mate.academy.springbootwebgreqit.model.ShoppingCart;
import mate.academy.springbootwebgreqit.model.User;
import mate.academy.springbootwebgreqit.service.ShoppingCartService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
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
    private CartItem cartItem;
    private ShoppingCart shoppingCart;

    @BeforeAll
    static void beforeAll(@Autowired WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Sql(scripts = {"/data-sql/create-books.sql", "/data-sql/create-users.sql",
            "/data-sql/create-shopping-carts.sql", "/data-sql/create-cart-item.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data-sql/clear-tables-for-sh.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @WithMockUser(username = "first_name", roles = {"USER"})
    @Test
    @DisplayName("Get shopping cart for the current user")
    void getShoppingCart_ShouldReturnCartForUser() throws Exception {
        mockMvc.perform(get("/cart")
                .param("userId", "1")  // Ensure the userId parameter is passed
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.id").value(1L))
                .andExpect(jsonPath("$.cartItems[0].book.id").value(1L))
                .andReturn();
    }

    @Sql(scripts = {"/data-sql/create-books.sql", "/data-sql/create-users.sql",
            "/data-sql/create-shopping-carts.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data-sql/clear-tables-for-sh.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @WithMockUser(username = "john_doe", roles = {"USER"})
    @Test
    @DisplayName("Add book to shopping cart")
    void addBookToShoppingCart_ShouldReturnUpdatedCart() throws Exception {
        CartItemRequestDto cartItemRequestDto = new CartItemRequestDto();
        cartItemRequestDto.setBookId(1L);
        cartItemRequestDto.setQuantity(2);

        String jsonRequest = objectMapper.writeValueAsString(cartItemRequestDto);

        MvcResult result = mockMvc.perform(post("/cart")
                        .param("userId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartItems[0].book.id").value(cartItemRequestDto.getBookId()))
                .andExpect(jsonPath("$.cartItems[0].quantity").value(cartItemRequestDto.getQuantity()))
                .andReturn();
    }

    @Sql(scripts = {"/data-sql/create-books.sql", "/data-sql/create-users.sql",
            "/data-sql/create-shopping-carts.sql", "/data-sql/create-cart-item.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data-sql/clear-tables-for-sh.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
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
                .andExpect(jsonPath("$.cartItems[0].quantity").value(newQuantity))
                .andReturn();
    }

    @Sql(scripts = {"/data-sql/create-books.sql", "/data-sql/create-users.sql",
            "/data-sql/create-shopping-carts.sql", "/data-sql/create-cart-item.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data-sql/clear-tables-for-sh.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @WithMockUser(username = "john_doe")
    @Test
    @DisplayName("Remove item from cart")
    void removeItemFromCart_ShouldReturnEmptyCart() throws Exception {
        Long cartItemId = 1L;

        mockMvc.perform(delete("/cart/items/{cartItemId}", cartItemId)
                        .param("userId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
