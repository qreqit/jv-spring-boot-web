package mate.academy.springbootwebgreqit.service;

import mate.academy.springbootwebgreqit.dto.order.OrderRequestDto;
import mate.academy.springbootwebgreqit.dto.order.OrderResponseDto;
import mate.academy.springbootwebgreqit.dto.orderItem.OrderItemResponseDto;
import mate.academy.springbootwebgreqit.model.User;

import java.util.List;
import java.util.Set;

public interface OrderService {
    OrderResponseDto AddOrder(OrderRequestDto requestDto, User user);

    List<OrderResponseDto> getAllOrders(User user);

    OrderResponseDto UpdateOrderStatus(Long id, OrderRequestDto requestDto);

    List<OrderItemResponseDto> GetAllItemsFromOrder(Long orderId);

    OrderItemResponseDto GetItemFromOrderById(Long orderId, Long itemId);
}
