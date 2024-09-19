package mate.academy.springbootwebgreqit.service;

import mate.academy.springbootwebgreqit.dto.cartitem.CartItemRequestDto;
import mate.academy.springbootwebgreqit.dto.shoppingcart.ShoppingCartDto;
import mate.academy.springbootwebgreqit.model.ShoppingCart;
import mate.academy.springbootwebgreqit.model.User;

public interface ShoppingCartService {
    ShoppingCartDto getShoppingCartForCurrentUser(Long userId);

    ShoppingCartDto addBookToShoppingCart(CartItemRequestDto cartItem, Long userId);

    ShoppingCartDto updateCartItemQuantity(Long cartItemId, int quantity, Long userId);

    ShoppingCartDto removeCartItem(Long cartItemId, Long userId);

    ShoppingCart createShoppingCart(User user);
}
