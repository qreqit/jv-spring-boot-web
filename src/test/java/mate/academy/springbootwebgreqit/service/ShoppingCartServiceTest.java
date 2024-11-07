//package mate.academy.springbootwebgreqit.service;
//
//import mate.academy.springbootwebgreqit.dto.cartitem.CartItemRequestDto;
//import mate.academy.springbootwebgreqit.dto.shoppingcart.ShoppingCartDto;
//import mate.academy.springbootwebgreqit.exception.EntityNotFoundException;
//import mate.academy.springbootwebgreqit.mapper.CartItemMapper;
//import mate.academy.springbootwebgreqit.mapper.ShoppingCartMapper;
//import mate.academy.springbootwebgreqit.model.Book;
//import mate.academy.springbootwebgreqit.model.CartItem;
//import mate.academy.springbootwebgreqit.model.ShoppingCart;
//import mate.academy.springbootwebgreqit.model.User;
//import mate.academy.springbootwebgreqit.repository.BookRepository;
//import mate.academy.springbootwebgreqit.repository.CartItemRepository;
//import mate.academy.springbootwebgreqit.repository.ShoppingCartRepository;
//import mate.academy.springbootwebgreqit.repository.UserRepository;
//import mate.academy.springbootwebgreqit.service.impl.ShoppingCartServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class ShoppingCartServiceTest {
//
//    @Mock
//    private CartItemRepository cartItemRepository;
//    @Mock
//    private ShoppingCartRepository shoppingCartRepository;
//    @Mock
//    private ShoppingCartMapper shoppingCartMapper;
//    @Mock
//    private CartItemMapper cartItemMapper;
//    @Mock
//    private BookRepository bookRepository;
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private ShoppingCartServiceImpl shoppingCartService;
//
//    private User user;
//    private ShoppingCart shoppingCart;
//    private ShoppingCartDto shoppingCartDto;
//    private CartItemRequestDto cartItemRequestDto;
//    private Book book;
//    private CartItem cartItem;
//
//    @BeforeEach
//    void setUp() {
//        user = new User();
//        user.setId(1L);
//
//        shoppingCart = new ShoppingCart();
//        shoppingCart.setUser(user);
//
//        shoppingCartDto = new ShoppingCartDto();
//
//        cartItemRequestDto = new CartItemRequestDto();
//        cartItemRequestDto.setBookId(1L);
//        cartItemRequestDto.setQuantity(2);
//
//        book = new Book();
//        book.setId(1L);
//
//        cartItem = new CartItem();
//        cartItem.setId(1L);
//        cartItem.setBook(book);
//        cartItem.setQuantity(2);
//        cartItem.setShoppingCart(shoppingCart);
//    }
//
////    @Test
////    void getShoppingCartForCurrentUser_ShouldReturnShoppingCartDto() {
////        user.setShoppingCart(shoppingCart);
////        // Arrange
////        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
////        when(shoppingCartMapper.toDto(shoppingCart)).thenReturn(shoppingCartDto);
////
////        // Act
////        ShoppingCartDto result = shoppingCartService.getShoppingCartForCurrentUser();
////
////        // Assert
////        assertNotNull(result);
////        verify(userRepository).findById(user.getId());
////        verify(shoppingCartMapper).toDto(shoppingCart);
////    }
//
////    @Test
////    void getShoppingCartForCurrentUser_ShouldThrowEntityNotFoundException_WhenShoppingCartNotFound() {
////        // Arrange
////        User user1 = new User();
////        user1.setId(2L);
////        user1.setShoppingCart(null);
////        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
////
////        // Act & Assert
////        assertThrows(EntityNotFoundException.class,
////                () -> shoppingCartService.getShoppingCartForCurrentUser(user1.getId()));
////    }
//
//
//    @Test
//    void addBookToShoppingCart_ShouldAddNewBookAndReturnShoppingCartDto() {
//        // Arrange
//        when(shoppingCartRepository.findByUserId(user.getId())).thenReturn(Optional.of(shoppingCart));
//        when(bookRepository.findById(cartItemRequestDto.getBookId())).thenReturn(Optional.of(book));
//        when(cartItemMapper.toModel(cartItemRequestDto)).thenReturn(cartItem);
//        when(shoppingCartMapper.toDto(shoppingCart)).thenReturn(shoppingCartDto);
//
//        // Mocking save method to return updated shopping cart
//        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenReturn(shoppingCart);
//
//        // Act
//        ShoppingCartDto result = shoppingCartService.addBookToShoppingCart(cartItemRequestDto, user.getId());
//
//        // Assert
//        assertNotNull(result);
//        verify(shoppingCartRepository).findByUserId(user.getId());
//        verify(cartItemMapper).toModel(cartItemRequestDto);
//        verify(bookRepository).findById(cartItemRequestDto.getBookId());
//        verify(shoppingCartRepository).save(shoppingCart);
//
//        // Optional: Verify that cart items were updated
//        assertEquals(1, shoppingCart.getCartItems().size()); // assuming one item is added
//    }
//
//
//    @Test
//    void updateCartItemQuantity_ShouldUpdateQuantityAndReturnShoppingCartDto() {
//        user.setShoppingCart(shoppingCart);
//        // Arrange
//        shoppingCart.getCartItems().add(cartItem);
//
//        // Мокування репозиторіїв
//        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));  // Перевірка на наявність користувача
//        when(shoppingCartMapper.toDto(shoppingCart)).thenReturn(shoppingCartDto);  // Мокування перетворення кошика в DTO
//        when(cartItemRepository.save(cartItem)).thenReturn(cartItem);  // Мокування збереження зміненої кількості товару
//        when(shoppingCartRepository.save(shoppingCart)).thenReturn(shoppingCart);
//
//        // Act
//        ShoppingCartDto result = shoppingCartService.updateCartItemQuantity(cartItem.getId(), 3, user.getId());
//
//        // Assert
//        assertNotNull(result);  // Перевірка, що результат не null
//        assertEquals(3, cartItem.getQuantity());  // Перевірка зміни кількості товару
//        verify(cartItemRepository).save(cartItem);  // Перевірка виклику методу збереження
//    }
//
//
//
//    @Test
//    void removeCartItem_ShouldRemoveItemAndReturnShoppingCartDto() {
//        // Arrange
//        shoppingCart.getCartItems().add(cartItem);
//        when(shoppingCartRepository.findByUserId(user.getId())).thenReturn(Optional.of(shoppingCart));
//        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
//        when(shoppingCartRepository.save(shoppingCart)).thenReturn(shoppingCart);
//
//        // Мокування для методу toDto
//        when(shoppingCartMapper.toDto(shoppingCart)).thenReturn(shoppingCartDto);
//
//        // Act
//        ShoppingCartDto result = shoppingCartService.removeCartItem(cartItem.getId(), user.getId());
//
//        // Assert
//        assertNotNull(result);
//        assertTrue(shoppingCart.getCartItems().isEmpty());
//        verify(shoppingCartRepository).save(shoppingCart);
//    }
//
//
//    @Test
//    void createShoppingCart_ShouldCreateAndReturnShoppingCart() {
//        // Act
//        ShoppingCart result = shoppingCartService.createShoppingCart(user);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(user, result.getUser());
//        verify(shoppingCartRepository).save(result);
//    }
//}
