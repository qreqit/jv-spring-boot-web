package mate.academy.springbootwebgreqit.controller;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.order.CreateOrderRequestDto;
import mate.academy.springbootwebgreqit.dto.order.OrderRequestDto;
import mate.academy.springbootwebgreqit.dto.order.OrderResponseDto;
import mate.academy.springbootwebgreqit.dto.orderItem.OrderItemResponseDto;
import mate.academy.springbootwebgreqit.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ApiOperation("Make order")
    @PreAuthorize("hasRole('USER')")
    public OrderResponseDto addOrder(@RequestParam Long userId,
                                     @RequestBody CreateOrderRequestDto createOrderRequestDto) {
        return orderService.addOrder(userId, createOrderRequestDto);
    }

    @ApiOperation("Get all orders")
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<OrderResponseDto> getAllOrders(@RequestParam Long userId) {
        return orderService.getAllOrders(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    @ApiOperation("Update order status by id")
    public OrderResponseDto updateOrderStatus(@PathVariable Long id,
                                              @RequestBody @Valid OrderRequestDto requestDto) {
        return orderService.updateOrderStatus(id, requestDto);
    }

    @GetMapping("/{orderId}/items")
    @ApiOperation("Get all items from order by order id")
    @PreAuthorize("hasRole('USER')")
    public List<OrderItemResponseDto> getAllItemsFromOrder(@PathVariable Long orderId,
                                                           Authentication authentication) {
        return orderService.getAllItemsFromOrder(orderId);
    }

    @GetMapping("{orderId}/items/{itemId}")
    @ApiOperation("Get item from order by order id and item id")
    @PreAuthorize("hasRole('USER')")
    public OrderItemResponseDto getItemFromOrderById(@PathVariable Long orderId,
                                                     @PathVariable Long itemId, Authentication authentication) {
        return orderService.getItemFromOrderById(orderId, itemId);
    }
}
