package mate.academy.springbootwebgreqit.mapper;

import mate.academy.springbootwebgreqit.dto.orderItem.OrderItemResponseDto;
import mate.academy.springbootwebgreqit.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(source = "book.id", target = "bookId")
    @Named(value = "toDto")
    OrderItemResponseDto toDto(OrderItem orderItem);

    @Mapping(source = "bookId", target = "book.id")
    OrderItem toModel(OrderItemResponseDto orderDto);
}
