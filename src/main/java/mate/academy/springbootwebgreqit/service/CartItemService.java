package mate.academy.springbootwebgreqit.service;

import mate.academy.springbootwebgreqit.dto.cartItem.CartItemRequestDto;
import mate.academy.springbootwebgreqit.dto.cartItem.CartItemResponseDto;

public interface CartItemService {
    CartItemResponseDto addCartItem(CartItemRequestDto requestDto);
}
