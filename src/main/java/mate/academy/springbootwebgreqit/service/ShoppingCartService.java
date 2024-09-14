package mate.academy.springbootwebgreqit.service;

import mate.academy.springbootwebgreqit.dto.cartItem.CartItemDto;
import mate.academy.springbootwebgreqit.dto.cartItem.CartItemRequestDto;
import mate.academy.springbootwebgreqit.dto.shoppingCart.ShoppingCartDto;
import mate.academy.springbootwebgreqit.model.CartItem;
import mate.academy.springbootwebgreqit.model.ShoppingCart;
import mate.academy.springbootwebgreqit.model.User;

public interface ShoppingCartService {
    ShoppingCartDto getShoppingCartForCurrentUser(Long userId);

    ShoppingCartDto addBookToShoppingCart(CartItemRequestDto cartItem, Long userId);

    ShoppingCartDto updateCartItemQuantity(Long cartItemId, int quantity, Long userId);

    ShoppingCartDto removeCartItem(Long cartItemId, Long userId);

    ShoppingCart createShoppingCart(User user);
}
