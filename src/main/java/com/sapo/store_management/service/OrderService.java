package com.sapo.store_management.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.sapo.store_management.dto.OrderRequest;
import com.sapo.store_management.model.Order;
import com.sapo.store_management.model.OrderProduct;
import com.sapo.store_management.repository.OrderProductRepository;
import com.sapo.store_management.repository.OrderRepository;
import com.sapo.store_management.repository.ProductRepository;
import jakarta.transaction.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, OrderProductRepository orderProductRepository,
            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Order handleCreateOrder(OrderRequest orderRequest) {
        if (orderRequest.getProducts() == null || orderRequest.getProducts().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one product.");
        }

        Order order = new Order();
        order.setCustomer_id(orderRequest.getCustomer_id());
        order.setCoupon_id(orderRequest.getCoupon_id());
        order.setStaff_id(orderRequest.getStaff_id());
        order.setTotal(orderRequest.getTotal());
        order.setPayment(orderRequest.getPayment());
        order.setChange_given(orderRequest.getChange_given());
        order.setStatus("ĐÃ THANH TOÁN");
        order.setNote(orderRequest.getNote());

        // Lưu đơn hàng lần đầu để sinh ID
        Order savedOrder = orderRepository.save(order);

        // Tạo mã đơn hàng dựa trên ID
        String orderCode = String.format("#%04d", savedOrder.getId());
        savedOrder.setCode(orderCode);
        orderRepository.save(savedOrder); // Lưu lại mã đơn hàng với mã mới

        // Xử lý sản phẩm trong đơn hàng
        List<OrderProduct> orderProducts = orderRequest.getProducts().stream()
                .map(req -> {
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setOrder(savedOrder);
                    orderProduct.setProduct(productRepository.findById(req.getProductId()).orElseThrow());
                    orderProduct.setQuantity(req.getQuantity());
                    orderProduct.setPrice(req.getPrice());
                    return orderProduct;
                })
                .collect(Collectors.toList());

        orderProductRepository.saveAll(orderProducts);

        savedOrder.setOrderProducts(orderProducts);

        return savedOrder;
    }

    public Page<Order> handleGetOrdersWithPagination(Pageable pageable) {
        return this.orderRepository.findAll(pageable);
    }

    public Order handleGetOrderById(int orderID) {
        Optional<Order> optionalOrder = orderRepository.findById(orderID);
        if (optionalOrder.isPresent()) {
            return optionalOrder.get();
        } else {
            return null;
        }
    }

    public Order findByCode(String code) {
        return orderRepository.findByCode(code);
    }

    @Transactional
    public Order handleUpdateOrder(int orderId, OrderRequest orderRequest) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        if (orderRequest.getProducts() == null || orderRequest.getProducts().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one product.");
        }

        existingOrder.setCustomer_id(orderRequest.getCustomer_id());
        existingOrder.setCoupon_id(orderRequest.getCoupon_id());
        existingOrder.setStaff_id(orderRequest.getStaff_id());
        existingOrder.setTotal(orderRequest.getTotal());
        existingOrder.setPayment(orderRequest.getPayment());
        existingOrder.setChange_given(orderRequest.getChange_given());
        existingOrder.setNote(orderRequest.getNote());

        List<OrderProduct> updateOrderProducts = orderRequest.getProducts().stream()
                .map(pro -> {
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setOrder(existingOrder);
                    orderProduct.setProduct(productRepository.findById(pro.getProductId())
                            .orElseThrow(() -> new RuntimeException("Not find")));
                    orderProduct.setQuantity(pro.getQuantity());
                    orderProduct.setPrice(pro.getPrice());
                    return orderProduct;
                }).collect(Collectors.toList());
        System.out.println(" getOrderProducts :  " + existingOrder.getOrderProducts().toString());
        orderProductRepository.deleteAll(existingOrder.getOrderProducts());

        orderProductRepository.saveAll(updateOrderProducts);

        existingOrder.setOrderProducts(updateOrderProducts);

        return orderRepository.save(existingOrder);
    }

    @Transactional
    public void handleDeleteOrder(int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));

        orderProductRepository.deleteAll(order.getOrderProducts());

        orderRepository.delete(order);
    }

}
