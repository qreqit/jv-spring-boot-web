package mate.academy.springbootwebgreqit.mapper;

import mate.academy.springbootwebgreqit.dto.orderItem.OrderItemDto;
import mate.academy.springbootwebgreqit.dto.orderItem.OrderItemResponseDto;
import mate.academy.springbootwebgreqit.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemResponseDto toDto(OrderItem orderItem);

    OrderItem toModel(OrderItemResponseDto orderDto);
}
