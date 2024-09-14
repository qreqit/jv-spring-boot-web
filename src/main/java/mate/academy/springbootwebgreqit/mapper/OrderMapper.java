package mate.academy.springbootwebgreqit.mapper;

import mate.academy.springbootwebgreqit.dto.order.OrderDto;
import mate.academy.springbootwebgreqit.dto.order.OrderRequestDto;
import mate.academy.springbootwebgreqit.dto.order.OrderResponseDto;
import mate.academy.springbootwebgreqit.model.Order;
import mate.academy.springbootwebgreqit.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toModel(OrderDto orderDto);

    @Mapping(source = "user.id", target = "userId")
    OrderResponseDto toDto(Order order);

    default Set<OrderItem> mapOrderItemIds(Set<Long> orderItemIds) {
        if (orderItemIds == null) {
            return null;
        }
        return orderItemIds.stream()
                .map(id -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setId(id);
                    return orderItem;
                })
                .collect(Collectors.toSet());
    }

    default Set<Long> mapOrderItem(Set<OrderItem> orderItems) {
        if (orderItems == null) {
            return null;
        }
        return orderItems.stream()
                .map(OrderItem::getId)
                .collect(Collectors.toSet());
    }
}
