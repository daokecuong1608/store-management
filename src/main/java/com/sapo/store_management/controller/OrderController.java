package com.sapo.store_management.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sapo.store_management.dto.OrderRequest;
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
    public ResponseEntity<Page<Order>> getOrders(Pageable pageable) {
        return ResponseEntity.ok(this.orderService.handleGetOrdersWithPagination(pageable));
    }

    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        Order createdOrder = orderService.handleCreateOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("{orderID}")
    public ResponseEntity<Order> updateOrder(@Valid @PathVariable int orderID, @RequestBody OrderRequest orderRequest) {
        Order updatedOrder = orderService.handleUpdateOrder(orderID, orderRequest);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("{orderID}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int orderID) {
        orderService.handleDeleteOrder(orderID);
        return ResponseEntity.noContent().build();
    }

}
