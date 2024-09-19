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
        if (shoppingCart == null) {
            throw new EntityNotFoundException("Shopping cart not found for user with ID "
                    + userId);
        }
        Hibernate.initialize(shoppingCart.getCartItems());
        Hibernate.initialize(user.getRoles());
        shoppingCart.getCartItems().forEach(cartItem ->
                Hibernate.initialize(cartItem.getBook().getCategories()));
        shoppingCart.getCartItems().forEach(cartItem ->
                Hibernate.initialize(cartItem.getBook().getCartItems()));
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Transactional
    @Override
    public ShoppingCartDto addBookToShoppingCart(
            CartItemRequestDto cartItemDto,
            Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping cart not found"));

        Optional<CartItem> existingItemOpt = shoppingCart.getCartItems().stream()
                .filter(item -> item.getBook().getId().equals(cartItemDto.getBookId()))
                .findFirst();

        existingItemOpt.ifPresentOrElse(
                existingItem -> existingItem.setQuantity(existingItem.getQuantity()
                        + cartItemDto.getQuantity()),
                () -> {
                    if (cartItemDto.getBookId() == null) {
                        throw new EntityNotFoundException("Book ID cannot be null");
                    }
                    CartItem newCartItem = cartItemMapper.toModel(cartItemDto);
                    newCartItem.setShoppingCart(shoppingCart);
                    newCartItem.setBook(bookRepository.findById(cartItemDto.getBookId())
                            .orElseThrow(() -> new EntityNotFoundException("Book not found")));
                    Hibernate.initialize(shoppingCart.getUser());
                    Hibernate.initialize(shoppingCart.getUser().getRoles());
                    newCartItem.setQuantity(cartItemDto.getQuantity());
                    newCartItem.setShoppingCart(shoppingCart);
                    shoppingCart.getCartItems().add(newCartItem);
                }
        );

        ShoppingCart savedshoppingCart = shoppingCartRepository.save(shoppingCart);
        Hibernate.initialize(savedshoppingCart.getCartItems());
        savedshoppingCart.getCartItems().forEach(cartItem ->
                Hibernate.initialize(cartItem.getBook().getCategories()));
        savedshoppingCart.getCartItems().forEach(cartItem ->
                Hibernate.initialize(cartItem.getBook().getCartItems()));

        return shoppingCartMapper.toDto(savedshoppingCart);
    }

    @Transactional
    @Override
    public ShoppingCartDto updateCartItemQuantity(Long cartItemId,
                                                  int quantity, Long userId) {
        if (quantity <= 0) {
            throw new EntityNotFoundException("Quantity must be a positive integer");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        ShoppingCart shoppingCart = user.getShoppingCart();
        if (shoppingCart == null) {
            throw new EntityNotFoundException("Shopping cart not found for user with ID "
                    + userId);
        }
        CartItem cartItem = shoppingCart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("CartItem with ID "
                        + cartItemId + " not found in the user's shopping cart"));

        cartItem.setQuantity(quantity);
        Hibernate.initialize(shoppingCart.getCartItems());
        Hibernate.initialize(user.getRoles());

        shoppingCart.getCartItems().forEach(ci ->
                Hibernate.initialize(cartItem.getBook().getCategories()));
        shoppingCart.getCartItems().forEach(ci ->
                Hibernate.initialize(cartItem.getBook().getCartItems()));

        cartItemRepository.save(cartItem);
        ShoppingCart savedShoppingCart = shoppingCartRepository.save(shoppingCart);

        return shoppingCartMapper.toDto(savedShoppingCart);
    }

    @Transactional
    @Override
    public ShoppingCartDto removeCartItem(Long cartItemId, Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping cart not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        CartItem cartItem = shoppingCart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("CartItem with ID "
                        + cartItemId + " not found"));

        shoppingCart.getCartItems().remove(cartItem);
        ShoppingCart savedshoppingCart = shoppingCartRepository.save(shoppingCart);

        Hibernate.initialize(shoppingCart.getCartItems());
        Hibernate.initialize(user.getRoles());

        shoppingCart.getCartItems().forEach(ci ->
                Hibernate.initialize(cartItem.getBook().getCategories()));
        shoppingCart.getCartItems().forEach(ci ->
                Hibernate.initialize(cartItem.getBook().getCartItems()));

        return shoppingCartMapper.toDto(savedshoppingCart);
    }

    @Override
    public ShoppingCart createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }
}
