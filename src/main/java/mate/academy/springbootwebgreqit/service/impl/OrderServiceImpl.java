package mate.academy.springbootwebgreqit.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.order.OrderRequestDto;
import mate.academy.springbootwebgreqit.dto.order.OrderResponseDto;
import mate.academy.springbootwebgreqit.dto.orderItem.OrderItemResponseDto;
import mate.academy.springbootwebgreqit.mapper.OrderItemMapper;
import mate.academy.springbootwebgreqit.mapper.OrderMapper;
import mate.academy.springbootwebgreqit.model.Order;
import mate.academy.springbootwebgreqit.model.User;
import mate.academy.springbootwebgreqit.repository.OrderItemReposirory;
import mate.academy.springbootwebgreqit.repository.OrderRepository;
import mate.academy.springbootwebgreqit.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemReposirory orderItemReposirory;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderResponseDto AddOrder(OrderRequestDto requestDto, User user) {
        return null;
    }

    @Override
    public List<OrderResponseDto> getAllOrders(User user) {
        return List.of();
    }

    @Override
    public OrderResponseDto UpdateOrderStatus(Long id, OrderRequestDto requestDto) {
        return null;
    }

    @Override
    public List<OrderItemResponseDto> GetAllItemsFromOrder(Long orderId) {
        return List.of();
    }

    @Override
    public OrderItemResponseDto GetItemFromOrderById(Long orderId, Long itemId) {
        return null;
    }
}
