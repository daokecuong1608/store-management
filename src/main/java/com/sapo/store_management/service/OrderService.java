package com.sapo.store_management.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sapo.store_management.dto.ProductRes;
import com.sapo.store_management.dto.order.OrderDTO;
import com.sapo.store_management.dto.order.OrderRequest;
import com.sapo.store_management.mapper.CustomerMapper;
import com.sapo.store_management.mapper.UserMapper;
import com.sapo.store_management.model.Order;
import com.sapo.store_management.model.OrderProduct;
import com.sapo.store_management.repository.CustomerRepository;
import com.sapo.store_management.repository.OrderProductRepository;
import com.sapo.store_management.repository.OrderRepository;
import com.sapo.store_management.repository.ProductRepository;
import com.sapo.store_management.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, OrderProductRepository orderProductRepository,
            ProductRepository productRepository, CustomerRepository customerRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
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

        Order savedOrder = orderRepository.save(order);

        String orderCode = String.format("#%04d", savedOrder.getId());
        savedOrder.setCode(orderCode);
        orderRepository.save(savedOrder);

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

    public Page<OrderDTO> handleGetOrdersWithPagination(Pageable pageable) {

        Page<Order> orders = orderRepository.findAll(pageable);

        return orders.map(order -> {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(order.getId());
            orderDTO.setCustomer_id(order.getCustomer_id());
            orderDTO.setCoupon_id(order.getCoupon_id());
            orderDTO.setStaff_id(order.getStaff_id());
            orderDTO.setCode(order.getCode());
            orderDTO.setTotal(order.getTotal());
            orderDTO.setPayment(order.getPayment());
            orderDTO.setChange_given(order.getChange_given());
            orderDTO.setStatus(order.getStatus());
            orderDTO.setNote(order.getNote());
            orderDTO.setCreated_at(order.getCreated_at());
            orderDTO.setUpdated_at(order.getUpdated_at());
            // orderDTO.setOrderProducts(order.getOrderProducts());

            // Ánh xạ OrderProduct -> chỉ lấy id và name của Product
            List<ProductRes> productDTOs = order.getOrderProducts().stream()
                    .map(orderProduct -> {
                        ProductRes productDTO = new ProductRes();
                        productDTO.setId(orderProduct.getProduct().getId());
                        productDTO.setName(orderProduct.getProduct().getName());
                        productDTO.setQuantity(orderProduct.getQuantity());
                        return productDTO;
                    })
                    .collect(Collectors.toList());

            orderDTO.setOrderProducts(productDTOs);


            UserMapper userMapper = new UserMapper();
            userRepository.findById(order.getStaff_id())
                    .ifPresent(user -> orderDTO.setStaff(userMapper.toDTO(user)));

            // Kiểm tra xem customer_id có tồn tại không
            if (order.getCustomer() != null) {
                CustomerMapper customerMapper = new CustomerMapper();
                customerRepository.findById(order.getCustomer_id())
                        .ifPresent(customer -> orderDTO.setCustomer(customerMapper.toDTO(customer)));
            } else {
                orderDTO.setCustomer(null);  // Nếu không có customer_id, có thể set null hoặc để trống
            }
            return orderDTO;
        });
    }

    public OrderDTO handleGetOrderById(int orderID) {
        return orderRepository.findById(orderID)
                .map(order -> {
                    OrderDTO orderDTO = new OrderDTO();
                    orderDTO.setId(order.getId());
                    orderDTO.setCustomer_id(order.getCustomer_id());
                    orderDTO.setCoupon_id(order.getCoupon_id());
                    orderDTO.setStaff_id(order.getStaff_id());
                    orderDTO.setCode(order.getCode());
                    orderDTO.setTotal(order.getTotal());
                    orderDTO.setPayment(order.getPayment());
                    orderDTO.setChange_given(order.getChange_given());
                    orderDTO.setStatus(order.getStatus());
                    orderDTO.setNote(order.getNote());
                    orderDTO.setCreated_at(order.getCreated_at());
                    orderDTO.setUpdated_at(order.getUpdated_at());
                    // orderDTO.setOrderProducts(order.getOrderProducts());

                    List<ProductRes> productDTOs = order.getOrderProducts().stream()
                            .map(orderProduct -> {
                                ProductRes productDTO = new ProductRes();
                                productDTO.setId(orderProduct.getProduct().getId());
                                productDTO.setName(orderProduct.getProduct().getName());
                                productDTO.setPrice(orderProduct.getProduct().getPrice());
                                productDTO.setQuantity(orderProduct.getQuantity());

                                productDTO.setImage(orderProduct.getProduct().getImages().isEmpty() ? null
                                        : orderProduct.getProduct().getImages().get(0).getImageUrl());
                                // productDTO.setImage(orderProduct.getProduct().getImages().get(0).getImageUrl());
                                return productDTO;
                            })
                            .collect(Collectors.toList());

                    orderDTO.setOrderProducts(productDTOs);

                    UserMapper userMapper = new UserMapper();
                    userRepository.findById(order.getStaff_id())
                            .ifPresent(user -> orderDTO.setStaff(userMapper.toDTO(user)));

                    CustomerMapper customerMapper = new CustomerMapper();
                    customerRepository.findById(order.getCustomer_id())
                            .ifPresent(customer -> orderDTO.setCustomer(customerMapper.toDTO(customer)));

                    return orderDTO;
                })
                .orElseThrow(() -> new RuntimeException("Order with ID " + orderID + " not found"));
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

    public Page<OrderDTO> searchByCodeOrPhone(String query, Pageable pageable) {

        Page<Order> orders = orderRepository.searchByCodeOrCustomerPhone(query, pageable);

        return orders.map(order -> {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(order.getId());
            orderDTO.setCustomer_id(order.getCustomer_id());
            orderDTO.setCoupon_id(order.getCoupon_id());
            orderDTO.setStaff_id(order.getStaff_id());
            orderDTO.setCode(order.getCode());
            orderDTO.setTotal(order.getTotal());
            orderDTO.setPayment(order.getPayment());
            orderDTO.setChange_given(order.getChange_given());
            orderDTO.setStatus(order.getStatus());
            orderDTO.setNote(order.getNote());
            orderDTO.setCreated_at(order.getCreated_at());
            orderDTO.setUpdated_at(order.getUpdated_at());
            // orderDTO.setOrderProducts(order.getOrderProducts());

            // Ánh xạ OrderProduct -> chỉ lấy id và name của Product
            List<ProductRes> productDTOs = order.getOrderProducts().stream()
                    .map(orderProduct -> {
                        ProductRes productDTO = new ProductRes();
                        productDTO.setId(orderProduct.getProduct().getId());
                        productDTO.setName(orderProduct.getProduct().getName());
                        return productDTO;
                    })
                    .collect(Collectors.toList());

            orderDTO.setOrderProducts(productDTOs);

            userRepository.findById(order.getStaff_id()).ifPresent(user -> {
                UserMapper userMapper = new UserMapper();
                orderDTO.setStaff(userMapper.toDTO(user));
            });

            customerRepository.findById(order.getCustomer_id()).ifPresent(customer -> {
                CustomerMapper customerMapper = new CustomerMapper();
                orderDTO.setCustomer(customerMapper.toDTO(customer));
            });

            return orderDTO;
        });
    }
}
