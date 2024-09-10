package mate.academy.springbootwebgreqit.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.cartItem.CartItemRequestDto;
import mate.academy.springbootwebgreqit.dto.shoppingCart.ShoppingCartDto;
import mate.academy.springbootwebgreqit.exception.EntityNotFoundException;
import mate.academy.springbootwebgreqit.mapper.CartItemMapper;
import mate.academy.springbootwebgreqit.mapper.ShoppingCartMapper;
import mate.academy.springbootwebgreqit.model.CartItem;
import mate.academy.springbootwebgreqit.model.ShoppingCart;
import mate.academy.springbootwebgreqit.model.User;
import mate.academy.springbootwebgreqit.repository.BookRepository;
import mate.academy.springbootwebgreqit.repository.CartItemRepository;
import mate.academy.springbootwebgreqit.repository.ShoppingCartRepository;
import mate.academy.springbootwebgreqit.repository.UserRepository;
import mate.academy.springbootwebgreqit.service.ShoppingCartService;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemMapper cartItemMapper;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public ShoppingCartDto getShoppingCartForCurrentUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        ShoppingCart shoppingCart = user.getShoppingCart();
        Hibernate.initialize(shoppingCart.getCartItems());
        Hibernate.initialize(user.getRoles());
        Hibernate.initialize(shoppingCart.getUser().getOrders());
        shoppingCart.getCartItems().forEach(cartItem -> Hibernate.initialize(cartItem.getBook().getCategories()));
        shoppingCart.getCartItems().forEach(cartItem -> Hibernate.initialize(cartItem.getBook().getOrderItems()));
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Transactional
    @Override
    public ShoppingCartDto addBookToShoppingCart(CartItemRequestDto cartItemDto) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartItemDto.getShoppingCartId())
                .orElseThrow(() -> new IllegalArgumentException("Shopping cart not found"));

        Optional<CartItem> existingItemOpt = shoppingCart.getCartItems().stream()
                .filter(item -> item.getBook().getId().equals(cartItemDto.getBookId()))
                .findFirst();

        existingItemOpt.ifPresentOrElse(
                existingItem -> existingItem.setQuantity(existingItem.getQuantity() + cartItemDto.getQuantity()),
                () -> {
                    if (cartItemDto.getBookId() == null) {
                        throw new IllegalArgumentException("Book ID cannot be null");
                    }
                    CartItem newCartItem = cartItemMapper.toModel(cartItemDto);
                    newCartItem.setShoppingCart(shoppingCart);
                    newCartItem.setBook(bookRepository.findById(cartItemDto.getBookId())
                            .orElseThrow(() -> new IllegalArgumentException("Book not found")));
                    Hibernate.initialize(shoppingCart.getUser());
                    Hibernate.initialize(shoppingCart.getUser().getRoles());
                    Hibernate.initialize(shoppingCart.getUser().getOrders());
                    newCartItem.setQuantity(cartItemDto.getQuantity());
                    newCartItem.setShoppingCart(shoppingCart);
                    shoppingCart.getCartItems().add(newCartItem);
                }
        );

        ShoppingCart savedshoppingCart = shoppingCartRepository.save(shoppingCart);
        Hibernate.initialize(savedshoppingCart.getCartItems());
        savedshoppingCart.getCartItems().forEach(cartItem -> Hibernate.initialize(cartItem.getBook().getCategories()));
        savedshoppingCart.getCartItems().forEach(cartItem -> Hibernate.initialize(cartItem.getBook().getOrderItems()));

        return shoppingCartMapper.toDto(savedshoppingCart);
    }

    @Transactional
    @Override
    public ShoppingCartDto updateCartItemQuantity(Long cartItemId, int quantity, Long userId) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive integer");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        ShoppingCart shoppingCart = user.getShoppingCart();
        CartItem cartItem = shoppingCart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("CartItem with ID " + cartItemId + " not found in the user's shopping cart"));

        cartItem.setQuantity(quantity);
        Hibernate.initialize(shoppingCart.getCartItems());
        Hibernate.initialize(user.getRoles());
        Hibernate.initialize(shoppingCart.getUser().getOrders());
        shoppingCart.getCartItems().forEach(ci -> Hibernate.initialize(cartItem.getBook().getCategories()));
        shoppingCart.getCartItems().forEach(ci -> Hibernate.initialize(cartItem.getBook().getOrderItems()));
        cartItemRepository.save(cartItem);
        ShoppingCart savedShoppingCart = shoppingCartRepository.save(shoppingCart);

        return shoppingCartMapper.toDto(savedShoppingCart);
    }

    @Transactional
    @Override
    public ShoppingCartDto removeCartItem(Long cartItemId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        ShoppingCart shoppingCart = user.getShoppingCart();
        CartItem cartItem = shoppingCart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() ->new  EntityNotFoundException("CartItem with ID " + cartItemId + " not found"));
        shoppingCart.getCartItems().remove(cartItem);
        ShoppingCart savedshoppingCart = shoppingCartRepository.save(shoppingCart);
        Hibernate.initialize(shoppingCart.getCartItems());
        Hibernate.initialize(user.getRoles());
        Hibernate.initialize(shoppingCart.getUser().getOrders());
        shoppingCart.getCartItems().forEach(ci -> Hibernate.initialize(cartItem.getBook().getCategories()));
        shoppingCart.getCartItems().forEach(ci -> Hibernate.initialize(cartItem.getBook().getOrderItems()));

        return shoppingCartMapper.toDto(savedshoppingCart);
    }
}