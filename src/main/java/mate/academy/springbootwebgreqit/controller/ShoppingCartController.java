package mate.academy.springbootwebgreqit.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.cartitem.CartItemRequestDto;
import mate.academy.springbootwebgreqit.dto.shoppingcart.ShoppingCartDto;
import mate.academy.springbootwebgreqit.service.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public ShoppingCartDto getShoppingCartForCurrentUser(Authentication authentication) {
        return shoppingCartService.getShoppingCartForCurrentUser(authentication);
    }

    @PostMapping
    public ShoppingCartDto addBookToShoppingCart(@RequestBody CartItemRequestDto cartItem,
                                                 Authentication authentication) {
        return shoppingCartService.addBookToShoppingCart(cartItem, authentication);
    }

    @PutMapping("/items/{cartItemId}")
    public ShoppingCartDto updateCartItemQuantity(@PathVariable Long cartItemId,
                                                  @RequestParam int quantity,
                                                  Authentication authentication) {
        return shoppingCartService.updateCartItemQuantity(cartItemId, quantity, authentication);
    }

    @DeleteMapping("/items/{cartItemId}")
    public void removeCartItem(@PathVariable Long cartItemId, Authentication authentication) {
        shoppingCartService.removeCartItem(cartItemId, authentication);
    }
}
