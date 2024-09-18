package mate.academy.springbootwebgreqit.mapper;

import mate.academy.springbootwebgreqit.dto.order.CreateOrderRequestDto;
import mate.academy.springbootwebgreqit.dto.order.OrderResponseDto;
import mate.academy.springbootwebgreqit.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrderMapper {
    Order toModel(CreateOrderRequestDto createOrderRequestDto);

    @Mapping(source = "user.id", target = "userId")
    OrderResponseDto toDto(Order order);
}
