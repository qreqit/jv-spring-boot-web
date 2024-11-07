package mate.academy.springbootwebgreqit.service;

import mate.academy.springbootwebgreqit.dto.cartitem.CartItemRequestDto;
import mate.academy.springbootwebgreqit.dto.shoppingcart.ShoppingCartDto;
import mate.academy.springbootwebgreqit.exception.EntityNotFoundException;
import mate.academy.springbootwebgreqit.mapper.CartItemMapper;
import mate.academy.springbootwebgreqit.mapper.ShoppingCartMapper;
import mate.academy.springbootwebgreqit.model.Book;
import mate.academy.springbootwebgreqit.model.CartItem;
import mate.academy.springbootwebgreqit.model.ShoppingCart;
import mate.academy.springbootwebgreqit.model.User;
import mate.academy.springbootwebgreqit.repository.BookRepository;
import mate.academy.springbootwebgreqit.repository.CartItemRepository;
import mate.academy.springbootwebgreqit.repository.ShoppingCartRepository;
import mate.academy.springbootwebgreqit.repository.UserRepository;
import mate.academy.springbootwebgreqit.service.impl.ShoppingCartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {

    @Mock
    private CartItemRepository cartItemRepository;
    @Mock
    private ShoppingCartRepository shoppingCartRepository;
    @Mock
    private ShoppingCartMapper shoppingCartMapper;
    @Mock
    private CartItemMapper cartItemMapper;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;

    private User user;
    private ShoppingCart shoppingCart;
    private ShoppingCartDto shoppingCartDto;
    private CartItemRequestDto cartItemRequestDto;
    private Book book;
    private CartItem cartItem;
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setUser(user);

        shoppingCartDto = new ShoppingCartDto();

        cartItemRequestDto = new CartItemRequestDto();
        cartItemRequestDto.setBookId(1L);
        cartItemRequestDto.setQuantity(2);

        book = new Book();
        book.setId(1L);

        cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setBook(book);
        cartItem.setQuantity(2);
        cartItem.setShoppingCart(shoppingCart);

        authentication = mock(Authentication.class);
    }

    @Test
    void getShoppingCartForCurrentUser_ShouldReturnShoppingCartDto() {
        user.setShoppingCart(shoppingCart);
        when(authentication.getName()).thenReturn(user.getEmail());
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(shoppingCartMapper.toDto(shoppingCart)).thenReturn(shoppingCartDto);

        ShoppingCartDto result = shoppingCartService.getShoppingCartForCurrentUser(authentication);

        assertNotNull(result);
        verify(userRepository).findByEmail(user.getEmail());
        verify(shoppingCartMapper).toDto(shoppingCart);
    }

    @Test
    void getShoppingCartForCurrentUser_ShouldThrowEntityNotFoundException_WhenShoppingCartNotFound() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(authentication.getName()).thenReturn(user.getEmail());

        assertThrows(EntityNotFoundException.class,
                () -> shoppingCartService.getShoppingCartForCurrentUser(authentication));
    }

    @Test
    void addBookToShoppingCart_ShouldAddNewBookAndReturnShoppingCartDto() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(shoppingCartRepository.findByUserId(user.getId())).thenReturn(Optional.of(shoppingCart));
        when(bookRepository.findById(cartItemRequestDto.getBookId())).thenReturn(Optional.of(book));
        when(cartItemMapper.toModel(cartItemRequestDto)).thenReturn(cartItem);
        when(shoppingCartMapper.toDto(shoppingCart)).thenReturn(shoppingCartDto);
        when(shoppingCartRepository.save(shoppingCart)).thenReturn(shoppingCart);
        when(authentication.getName()).thenReturn(user.getEmail());

        ShoppingCartDto result = shoppingCartService.addBookToShoppingCart(cartItemRequestDto, authentication);

        assertNotNull(result);
        verify(shoppingCartRepository).findByUserId(user.getId());
        verify(cartItemMapper).toModel(cartItemRequestDto);
        verify(bookRepository).findById(cartItemRequestDto.getBookId());
        verify(shoppingCartRepository).save(shoppingCart);
        assertEquals(1, shoppingCart.getCartItems().size());
    }

    @Test
    void updateCartItemQuantity_ShouldUpdateQuantityAndReturnShoppingCartDto() {
        user.setShoppingCart(shoppingCart);
        shoppingCart.getCartItems().add(cartItem);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(cartItemRepository.save(cartItem)).thenReturn(cartItem);
        when(shoppingCartRepository.save(shoppingCart)).thenReturn(shoppingCart);
        when(shoppingCartMapper.toDto(shoppingCart)).thenReturn(shoppingCartDto);
        when(authentication.getName()).thenReturn(user.getEmail());

            ShoppingCartDto result = shoppingCartService.updateCartItemQuantity(cartItem.getId(), 3, authentication);

        assertNotNull(result);
        assertEquals(3, cartItem.getQuantity());
        verify(cartItemRepository).save(cartItem);
        verify(shoppingCartMapper).toDto(shoppingCart);
    }

    @Test
    void removeCartItem_ShouldRemoveItemAndReturnShoppingCartDto() {
        shoppingCart.getCartItems().add(cartItem);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(shoppingCartRepository.findByUserId(user.getId())).thenReturn(Optional.of(shoppingCart));
        when(shoppingCartRepository.save(shoppingCart)).thenReturn(shoppingCart);
        when(shoppingCartMapper.toDto(shoppingCart)).thenReturn(shoppingCartDto);
        when(authentication.getName()).thenReturn(user.getEmail());

        ShoppingCartDto result = shoppingCartService.removeCartItem(cartItem.getId(), authentication);

        assertNotNull(result);
        assertTrue(shoppingCart.getCartItems().isEmpty());
        verify(shoppingCartRepository).save(shoppingCart);
    }

    @Test
    void createShoppingCart_ShouldCreateAndReturnShoppingCart() {
        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenReturn(shoppingCart);

        ShoppingCart result = shoppingCartService.createShoppingCart(user);

        assertNotNull(result);
        assertEquals(user, result.getUser());
        verify(shoppingCartRepository).save(result);
    }
}