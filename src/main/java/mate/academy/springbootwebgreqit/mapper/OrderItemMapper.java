package mate.academy.springbootwebgreqit.mapper;

import mate.academy.springbootwebgreqit.dto.orderItem.OrderItemDto;
import mate.academy.springbootwebgreqit.dto.orderItem.OrderItemResponseDto;
import mate.academy.springbootwebgreqit.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(source = "book.id", target = "bookId")
    OrderItemResponseDto toDto(OrderItem orderItem);

    OrderItem toModel(OrderItemResponseDto orderDto);
}
