package mate.academy.springbootwebgreqit.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.cartItem.CartItemDto;
import mate.academy.springbootwebgreqit.dto.shoppingCart.ShoppingCartDto;
import mate.academy.springbootwebgreqit.exception.EntityNotFoundException;
import mate.academy.springbootwebgreqit.mapper.CartItemMapper;
import mate.academy.springbootwebgreqit.mapper.ShoppingCartMapper;
import mate.academy.springbootwebgreqit.model.CartItem;
import mate.academy.springbootwebgreqit.model.ShoppingCart;
import mate.academy.springbootwebgreqit.model.User;
import mate.academy.springbootwebgreqit.repository.CartItemRepository;
import mate.academy.springbootwebgreqit.repository.ShoppingCartRepository;
import mate.academy.springbootwebgreqit.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemMapper cartItemMapper;

    @Override
    public ShoppingCartDto getShoppingCartForCurrentUser(User user) {
        ShoppingCart shoppingCart = user.getShoppingCart();
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto addBookToShoppingCart(CartItemDto cartItemDto, User user) {
        ShoppingCart shoppingCart = user.getShoppingCart();

        Optional<CartItem> existingItemOpt = shoppingCart.getCartItems().stream()
                .filter(item -> item.getBook().getId().equals(cartItemDto.getBookId()))
                .findFirst();

        existingItemOpt.ifPresentOrElse(
                existingItem -> existingItem.setQuantity(existingItem.getQuantity() + cartItemDto.getQuantity()),
                () -> {
                    CartItem newCartItem = cartItemMapper.toModel(cartItemDto);
                    newCartItem.setShoppingCart(shoppingCart);
                    shoppingCart.getCartItems().add(newCartItem);
                }
        );

        ShoppingCart savedshoppingCart = shoppingCartRepository.save(shoppingCart);

        return shoppingCartMapper.toDto(savedshoppingCart);
    }

    @Override
    public ShoppingCartDto updateCartItemQuantity(Long cartItemId, int quantity, User user) {
        ShoppingCart shoppingCart = user.getShoppingCart();
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("Can't update cart item by quantity"));
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto removeCartItem(Long cartItemId, User user) {
        ShoppingCart shoppingCart = user.getShoppingCart();
        CartItem cartItem = shoppingCart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() ->new  EntityNotFoundException("CartItem with ID " + cartItemId + " not found"));
        shoppingCart.getCartItems().remove(cartItem);
        ShoppingCart savedshoppingCart = shoppingCartRepository.save(shoppingCart);

        return shoppingCartMapper.toDto(savedshoppingCart);
    }
}
