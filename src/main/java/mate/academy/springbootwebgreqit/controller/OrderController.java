package mate.academy.springbootwebgreqit.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.order.OrderRequestDto;
import mate.academy.springbootwebgreqit.dto.order.OrderResponseDto;
import mate.academy.springbootwebgreqit.dto.orderItem.OrderItemResponseDto;
import mate.academy.springbootwebgreqit.model.User;
import mate.academy.springbootwebgreqit.service.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderResponseDto addOrder(@RequestBody OrderRequestDto requestDto, @AuthenticationPrincipal User user) {
        return orderService.addOrder(requestDto, user);
    }

    @GetMapping
    public List<OrderResponseDto> getAllOrders(@AuthenticationPrincipal User user) {
        return orderService.getAllOrders(user);
    }

    @PatchMapping("/{id}")
    public OrderResponseDto updateOrderStatus(@PathVariable Long id, @RequestBody OrderRequestDto requestDto) {
        return orderService.updateOrderStatus(id, requestDto);
    }

    @GetMapping("{orderId}/items")
    public List<OrderItemResponseDto> getAllItemsFromOrder(@PathVariable Long orderId) {
        return orderService.getAllItemsFromOrder(orderId);
    }

    @GetMapping("{orderId}/items/{itemId}")
    public OrderItemResponseDto getItemFromOrderById(@PathVariable Long orderId, @PathVariable Long itemId) {
        return orderService.getItemFromOrderById(orderId, itemId);
    }
}