package com.sapo.store_management.service;

import java.util.List;
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

}
