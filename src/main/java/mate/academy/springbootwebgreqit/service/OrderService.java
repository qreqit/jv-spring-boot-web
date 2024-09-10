package mate.academy.springbootwebgreqit.service;

import mate.academy.springbootwebgreqit.dto.order.OrderRequestDto;
import mate.academy.springbootwebgreqit.dto.order.OrderResponseDto;
import mate.academy.springbootwebgreqit.dto.orderItem.OrderItemResponseDto;
import mate.academy.springbootwebgreqit.model.User;

import java.util.List;
import java.util.Set;

public interface OrderService {
    OrderResponseDto addOrder(Long userId);

    List<OrderResponseDto> getAllOrders(Long userId);

    OrderResponseDto updateOrderStatus(Long id, OrderRequestDto requestDto);

    List<OrderItemResponseDto> getAllItemsFromOrder(Long orderId);

    OrderItemResponseDto getItemFromOrderById(Long orderId, Long itemId);
}
