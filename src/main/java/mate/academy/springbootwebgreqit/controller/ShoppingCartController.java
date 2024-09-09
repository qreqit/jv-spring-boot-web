package mate.academy.springbootwebgreqit.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.cartItem.CartItemRequestDto;
import mate.academy.springbootwebgreqit.dto.shoppingCart.ShoppingCartDto;
import mate.academy.springbootwebgreqit.model.User;
import mate.academy.springbootwebgreqit.service.ShoppingCartService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public ShoppingCartDto getShoppingCartForCurrentUser(@RequestParam Long userId) {
        return shoppingCartService.getShoppingCartForCurrentUser(userId);
    }

    @PostMapping
    public ShoppingCartDto addBookToShoppingCart(@RequestBody CartItemRequestDto cartItem) {
    return shoppingCartService.addBookToShoppingCart(cartItem);
    }

    @PutMapping("/items/{cartItemId}")
    public ShoppingCartDto updateCartItemQuantity(@PathVariable Long cartItemId, @RequestParam int quantity, @RequestParam Long userId) {
        return shoppingCartService.updateCartItemQuantity(cartItemId, quantity, userId);
    }

    @DeleteMapping("/items/{cartItemId}")
    public void removeCartItem(@PathVariable Long cartItemId, @RequestParam Long userId) {
        shoppingCartService.removeCartItem(cartItemId, userId);
    }
}
