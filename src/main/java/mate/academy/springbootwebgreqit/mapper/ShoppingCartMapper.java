package mate.academy.springbootwebgreqit.mapper;

import mate.academy.springbootwebgreqit.dto.shoppingCart.ShoppingCartDto;
import mate.academy.springbootwebgreqit.model.ShoppingCart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShoppingCartMapper {
    ShoppingCartDto toDto(ShoppingCart shoppingCart);

    ShoppingCart toModel(ShoppingCartDto shoppingCartDto);
}
