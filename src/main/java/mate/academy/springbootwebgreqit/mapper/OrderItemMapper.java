package mate.academy.springbootwebgreqit.mapper;

import mate.academy.springbootwebgreqit.dto.order.OrderDto;
import mate.academy.springbootwebgreqit.dto.order.OrderRequestDto;
import mate.academy.springbootwebgreqit.model.Order;
import mate.academy.springbootwebgreqit.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderRequestDto toDto(OrderItem orderItem);

    Order toModel(OrderDto orderDto);
}
