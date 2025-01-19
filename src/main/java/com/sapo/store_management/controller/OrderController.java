package com.sapo.store_management.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sapo.store_management.dto.order.OrderDTO;
import com.sapo.store_management.dto.order.OrderRequest;
import com.sapo.store_management.model.Order;
import com.sapo.store_management.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public ResponseEntity<Page<OrderDTO>> getOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "false") boolean descending) {

        System.out.println(sortBy);
        Pageable pageable;
        if (sortBy != null && !sortBy.isEmpty()) {
            pageable = descending
                    ? PageRequest.of(page, size, Sort.by(sortBy).descending())
                    : PageRequest.of(page, size, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, size);
        }

        Page<OrderDTO> orders = this.orderService.handleGetOrdersWithPagination(pageable);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderID}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable int orderID) {
        OrderDTO order = orderService.handleGetOrderById(orderID);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchOrder(@RequestParam String query, Pageable pageable) {
        Page<OrderDTO> orders = orderService.searchByCodeOrPhone(query, pageable);
        if (orders != null && !orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No orders found for query: " + query);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        Order createdOrder = orderService.handleCreateOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/{orderID}")
    public ResponseEntity<Order> updateOrder(@Valid @PathVariable int orderID, @RequestBody OrderRequest orderRequest) {
        Order updatedOrder = orderService.handleUpdateOrder(orderID, orderRequest);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{orderID}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int orderID) {
        orderService.handleDeleteOrder(orderID);
        return ResponseEntity.noContent().build();
    }

}
