package mate.academy.springbootwebgreqit.mapper;

import mate.academy.springbootwebgreqit.dto.cartItem.CartItemRequestDto;
import mate.academy.springbootwebgreqit.dto.cartItem.CartItemResponseDto;
import mate.academy.springbootwebgreqit.model.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemResponseDto toDto(CartItem CartItem);

    CartItem toModel(CartItemRequestDto CartItemDto);
}
