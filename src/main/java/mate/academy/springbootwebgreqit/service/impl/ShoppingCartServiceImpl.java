package mate.academy.springbootwebgreqit.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.cartitem.CartItemRequestDto;
import mate.academy.springbootwebgreqit.dto.shoppingcart.RequestUpdateQuantityDto;
import mate.academy.springbootwebgreqit.dto.shoppingcart.ShoppingCartDto;
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
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

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
    public ShoppingCartDto getShoppingCartForCurrentUser(Authentication authentication,
                                                         Long shoppingCartId) {
        User authUser = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: "
                        + authentication.getName()));
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping cart not "
                        + "found for user with id: " + shoppingCartId));
        if (shoppingCart == null) {
            throw new EntityNotFoundException("Shopping cart not found for user with email: "
                    + authUser.getEmail());
        }
        Hibernate.initialize(shoppingCart.getCartItems());
        Hibernate.initialize(authUser.getRoles());
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
            Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: "
                        + authentication.getName()));
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(user.getId())
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
                                                  RequestUpdateQuantityDto quantity,
                                                  Authentication authentication) {
        if (quantity.getQuantity() <= 0) {
            throw new EntityNotFoundException("Quantity must be a positive integer");
        }
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: "
                        + authentication.getName()));

        CartItem cartItemToFindShoppingCart = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("Cart item not found with id: "
                        + cartItemId));

        ShoppingCart shoppingCart = shoppingCartRepository
                .findByCartItems(Set.of(cartItemToFindShoppingCart))
                .orElseThrow(() -> new EntityNotFoundException("Shopping cart "
                        + "not found by cart item with id: " + cartItemToFindShoppingCart));
        if (shoppingCart == null) {
            throw new EntityNotFoundException("Shopping cart not found for user with ID "
                    + user.getId());
        }
        CartItem cartItem = shoppingCart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("CartItem with ID "
                        + cartItemId + " not found in the user's shopping cart"));

        cartItem.setQuantity(quantity.getQuantity());
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
    public ShoppingCartDto removeCartItem(Long cartItemId, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: "
                        + authentication.getName()));
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Shopping cart not found"));

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
