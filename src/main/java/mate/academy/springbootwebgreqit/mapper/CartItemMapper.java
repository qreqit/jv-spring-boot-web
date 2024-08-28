package mate.academy.springbootwebgreqit.mapper;

import mate.academy.springbootwebgreqit.dto.cartItem.CartItemDto;
import mate.academy.springbootwebgreqit.model.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemDto toDto(CartItem CartItem);

    CartItem toModel(CartItemDto CartItemDto);
}
