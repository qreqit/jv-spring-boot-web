package mate.academy.springbootwebgreqit.service;

import mate.academy.springbootwebgreqit.dto.order.CreateOrderRequestDto;
import mate.academy.springbootwebgreqit.dto.order.OrderRequestDto;
import mate.academy.springbootwebgreqit.dto.order.OrderResponseDto;
import mate.academy.springbootwebgreqit.dto.orderitem.OrderItemResponseDto;

import java.util.List;

public interface OrderService {
    OrderResponseDto addOrder(Long userId, CreateOrderRequestDto createOrderRequestDto);

    List<OrderResponseDto> getAllOrders(Long userId);

    OrderResponseDto updateOrderStatus(Long id, OrderRequestDto requestDto);

    List<OrderItemResponseDto> getAllItemsFromOrder(Long orderId);

    OrderItemResponseDto getItemFromOrderById(Long orderId, Long itemId);
}
