package mate.academy.springbootwebgreqit.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.cartItem.CartItemDto;
import mate.academy.springbootwebgreqit.dto.shoppingCart.ShoppingCartDto;
import mate.academy.springbootwebgreqit.model.User;
import mate.academy.springbootwebgreqit.service.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public ShoppingCartDto getShoppingCartForCurrentUser(User user) {
        return shoppingCartService.getShoppingCartForCurrentUser(user);
    }

    @PostMapping
    public ShoppingCartDto addBookToShoppingCart(CartItemDto cartItem, User user) {
    return shoppingCartService.addBookToShoppingCart(cartItem, user);
    }

    @PutMapping("/items/{cartItemId}")
    public ShoppingCartDto updateCartItemQuantity(Long cartItemId, int quantity, User user) {
        return shoppingCartService.updateCartItemQuantity(cartItemId, quantity, user);
    }

    @DeleteMapping("/items/{cartItemId}")
    public void removeCartItem(Long cartItemId, User user) {
        shoppingCartService.removeCartItem(cartItemId, user);
    }
}
