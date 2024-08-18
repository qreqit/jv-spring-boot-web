package mate.academy.springbootwebgreqit.service;

import mate.academy.springbootwebgreqit.dto.cartItem.CartItemDto;
import mate.academy.springbootwebgreqit.dto.shoppingCart.ShoppingCartDto;
import mate.academy.springbootwebgreqit.model.CartItem;
import mate.academy.springbootwebgreqit.model.User;

public interface ShoppingCartService {
    ShoppingCartDto getShoppingCartForCurrentUser(User user);

    ShoppingCartDto addBookToShoppingCart(CartItemDto cartItem, User user);

    ShoppingCartDto updateCartItemQuantity(Long cartItemId, int quantity, User user);

    ShoppingCartDto removeCartItem(Long cartItemId, User user);
}
