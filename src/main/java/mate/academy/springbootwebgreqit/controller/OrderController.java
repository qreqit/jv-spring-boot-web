package mate.academy.springbootwebgreqit.controller;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.order.OrderDto;
import mate.academy.springbootwebgreqit.dto.order.OrderRequestDto;
import mate.academy.springbootwebgreqit.dto.order.OrderResponseDto;
import mate.academy.springbootwebgreqit.dto.orderItem.OrderItemResponseDto;
import mate.academy.springbootwebgreqit.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ApiOperation("Make order")
    public OrderResponseDto addOrder(@RequestParam Long userId, @RequestBody OrderDto orderDto) {
        return orderService.addOrder(userId, orderDto);
    }

    @ApiOperation("Get all orders")
    @GetMapping
    public List<OrderResponseDto> getAllOrders(@RequestParam Long userId) {
        return orderService.getAllOrders(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    @ApiOperation("Update order status by id")
    public OrderResponseDto updateOrderStatus(@PathVariable Long id, @RequestBody @Valid OrderRequestDto requestDto) {
        return orderService.updateOrderStatus(id, requestDto);
    }

    @GetMapping("/{orderId}/items")
    @ApiOperation("Get all items from order by order id")
    public List<OrderItemResponseDto> getAllItemsFromOrder(@PathVariable Long orderId) {
        return orderService.getAllItemsFromOrder(orderId);
    }

    @GetMapping("{orderId}/items/{itemId}")
    @ApiOperation("Get item from order by order id and item id")
    public OrderItemResponseDto getItemFromOrderById(@PathVariable Long orderId, @PathVariable Long itemId) {
        return orderService.getItemFromOrderById(orderId, itemId);
    }
}