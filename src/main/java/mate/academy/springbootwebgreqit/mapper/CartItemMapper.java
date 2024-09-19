package mate.academy.springbootwebgreqit.mapper;

import mate.academy.springbootwebgreqit.dto.cartitem.CartItemRequestDto;
import mate.academy.springbootwebgreqit.dto.cartitem.CartItemResponseDto;
import mate.academy.springbootwebgreqit.model.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemResponseDto toDto(CartItem cartItem);

    CartItem toModel(CartItemRequestDto cartItemDto);
}
