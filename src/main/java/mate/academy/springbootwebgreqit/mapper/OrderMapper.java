package mate.academy.springbootwebgreqit.mapper;

import mate.academy.springbootwebgreqit.dto.order.OrderDto;
import mate.academy.springbootwebgreqit.dto.order.OrderRequestDto;
import mate.academy.springbootwebgreqit.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toModel(OrderRequestDto requestDto);

    OrderRequestDto toDto(Order order);
}
