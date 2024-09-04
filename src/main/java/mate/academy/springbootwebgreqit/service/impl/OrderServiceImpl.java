package mate.academy.springbootwebgreqit.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.order.OrderRequestDto;
import mate.academy.springbootwebgreqit.dto.order.OrderResponseDto;
import mate.academy.springbootwebgreqit.dto.orderItem.OrderItemResponseDto;
import mate.academy.springbootwebgreqit.exception.EntityNotFoundException;
import mate.academy.springbootwebgreqit.mapper.OrderItemMapper;
import mate.academy.springbootwebgreqit.mapper.OrderMapper;
import mate.academy.springbootwebgreqit.model.Order;
import mate.academy.springbootwebgreqit.model.OrderItem;
import mate.academy.springbootwebgreqit.model.User;
import mate.academy.springbootwebgreqit.repository.OrderItemReposirory;
import mate.academy.springbootwebgreqit.repository.OrderRepository;
import mate.academy.springbootwebgreqit.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemReposirory orderItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderResponseDto addOrder(OrderRequestDto requestDto, User user) {
        Order order = orderMapper.toModel(requestDto);
        order.setUser(user);
        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderResponseDto> getAllOrders(User user) {
        return orderRepository.findByUser(user).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderResponseDto updateOrderStatus(Long id, OrderRequestDto requestDto) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setStatus(requestDto.getStatus());
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderItemResponseDto> getAllItemsFromOrder(Long orderId) {
        return orderItemRepository.findByOrderId(orderId).stream()
                .map(orderItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemResponseDto getItemFromOrderById(Long orderId, Long itemId) {
        OrderItem orderItem = orderItemRepository.findByOrderIdAndId(orderId, itemId)
                .orElseThrow(() -> new RuntimeException("Order item not found"));
        return orderItemMapper.toDto(orderItem);
    }
}