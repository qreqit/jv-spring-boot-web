package mate.academy.springbootwebgreqit.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.order.OrderRequestDto;
import mate.academy.springbootwebgreqit.dto.order.OrderResponseDto;
import mate.academy.springbootwebgreqit.dto.orderItem.OrderItemResponseDto;
import mate.academy.springbootwebgreqit.exception.EntityNotFoundException;
import mate.academy.springbootwebgreqit.mapper.OrderItemMapper;
import mate.academy.springbootwebgreqit.mapper.OrderMapper;
import mate.academy.springbootwebgreqit.model.Order;
import mate.academy.springbootwebgreqit.model.OrderItem;
import mate.academy.springbootwebgreqit.model.ShoppingCart;
import mate.academy.springbootwebgreqit.model.User;
import mate.academy.springbootwebgreqit.repository.OrderItemReposirory;
import mate.academy.springbootwebgreqit.repository.OrderRepository;
import mate.academy.springbootwebgreqit.repository.ShoppingCartRepository;
import mate.academy.springbootwebgreqit.repository.UserRepository;
import mate.academy.springbootwebgreqit.service.OrderService;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemReposirory orderItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public OrderResponseDto addOrder(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        ShoppingCart shoppingCart = user.getShoppingCart();
        if (shoppingCart == null || shoppingCart.getCartItems().isEmpty()) {
            throw new IllegalArgumentException("Shopping cart is empty");
        }

        BigDecimal total = shoppingCart.getCartItems().stream()
                .map(item -> item.getBook().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        final Order order = new Order();
        order.setUser(user);
        order.setStatus(Order.Status.PENDING);
        order.setTotal(total);
        order.setOrderDate(LocalDateTime.now());
        order.setShippingAddress(user.getShippingAddress());

        Hibernate.initialize(user.getOrders());
        Hibernate.initialize(user.getId());

        Set<OrderItem> orderItems = shoppingCart.getCartItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setBook(cartItem.getBook());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setPrice(cartItem.getBook().getPrice());
                    return orderItem;
                })
                .collect(Collectors.toSet());

        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);
        Hibernate.initialize(savedOrder.getOrderItems());
        savedOrder.getOrderItems().forEach(orderItem -> Hibernate.initialize(orderItem.getBook()));

        shoppingCart.getCartItems().clear();
        shoppingCartRepository.save(shoppingCart);

        Hibernate.initialize(shoppingCart.getCartItems());
        shoppingCart.getCartItems().forEach(cartItem -> Hibernate.initialize(cartItem.getBook()));

        return orderMapper.toDto(order);
    }

    @Override
    @Transactional
    public List<OrderResponseDto> getAllOrders(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Hibernate.initialize(user.getOrders());
        user.getOrders().forEach(order -> {
            Hibernate.initialize(order.getOrderItems());
            order.getOrderItems().forEach(orderItem -> Hibernate.initialize(orderItem.getBook()));
        });
        return orderRepository.findByUser(user).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
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
